package Service;

import Model.LineaPedidoModel;
import Model.PedidoModel;
import Repository.CarritoRepository;
import Repository.PedidoRepository;
import Repository.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ApplicationScoped
public class PedidoService {
    @Inject
    CarritoRepository carritoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ProductoRepository productoRepository; // para validar y sincronizar stock

    @Inject CatalogoService catalogoService; // usa lógica de descuento

    @Transactional
    public PedidoModel confirmar(Long carritoId){
        var carrito = carritoRepository.findById(carritoId);
        if (carrito == null) throw new IllegalStateException("Carrito no existe");
        if (carrito.getLineas().isEmpty()) throw new IllegalStateException("Carrito vacío");

        // Calcular total y validar stock por cada línea (otra vez, por seguridad)
        BigDecimal total = BigDecimal.ZERO;
        var lineasPedido = new ArrayList<LineaPedidoModel>();

        for (var lc : carrito.getLineas()){
            var producto = productoRepository.findById(lc.getProducto().getId());
            if(producto == null) throw new IllegalStateException("producto no existe");
            int cantidad = lc.getCantidad();

            // Regla stock mínimo al confirmar
            int nuevo = producto.getStock() - cantidad;
            if (nuevo < producto.getStockMinimo()){
                throw new IllegalStateException("Stock insuficiente para confirmar pedido (producto: " + producto.getSku() + ")");
            }

            BigDecimal precioUnit = lc.getPrecioUnitario();
            total = total.add(precioUnit.multiply(BigDecimal.valueOf(cantidad)));

            var lp = new LineaPedidoModel(null,producto,cantidad,precioUnit);
            lineasPedido.add(lp);
        }

        //crear pedido
        var pedido = new PedidoModel(OffsetDateTime.now(),total);
        for (var lp : lineasPedido){
            lp.setPedido(pedido);
            pedido.getLineas().add(lp);
        }
        pedidoRepository.persist(pedido);

        // Descontar stock (atómico dentro de la misma transacción)
        for (var lc: carrito.getLineas()){
            catalogoService.descontarStock(lc.getProducto().getId(), lc.getCantidad());
        }

        // Vaciar carrito (opcional)
        carrito.getLineas().clear();

        return pedido;
    }
}
