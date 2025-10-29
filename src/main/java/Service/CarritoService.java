package Service;

import Model.CarritoModel;
import Model.LineaCarritoModel;
import Repository.CarritoRepository;
import Repository.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@ApplicationScoped
public class CarritoService {

    @Inject
    CarritoRepository carritoRepository;

    @Inject
    ProductoRepository productoRepository;

    public CarritoModel crear(){
        var c = new CarritoModel();
        carritoRepository.persist(c);
        return c;
    }

    @Transactional
    public CarritoModel agregarLinea(Long carritoId, Long productoId, int cantidad){
        var carrito = carritoRepository.findById(carritoId);
        if (carrito == null) throw new IllegalMonitorStateException("carrito no existe");

        var producto = productoRepository.findById(productoId);
        if (producto == null) throw new IllegalStateException("Producto no existe");
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad > 0 requerida");

        // Regla: stock mínimo
        int nuevo = producto.getStock() - cantidad;
        if(nuevo < producto.getStockMinimo()){
            throw new IllegalStateException("Stock insuficiente respetando stock mínimo");
        }

        // Precio vigente al momento de agregar
        BigDecimal precioUnit = producto.getPrecio();

        var linea = new LineaCarritoModel(carrito,producto,cantidad,precioUnit);
        linea.setCarrito(carrito);
        linea.setProducto(producto);
        carrito.getLineas().add(linea);

        // NO descontamos stock aquí; se descuenta al confirmar pedido.
        // Persistencia en cascada por @OneToMany(cascade = ALL)
        return carrito;
    }

    @Transactional
    public void vaciar(Long carritoId){
        var carrito = carritoRepository.findById(carritoId);
        if(carrito == null)
            return;
        carrito.getLineas().clear();
    }

}
