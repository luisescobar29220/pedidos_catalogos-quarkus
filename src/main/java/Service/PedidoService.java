package Service;

import Repository.CarritoRepository;
import Repository.PedidoRepository;
import Repository.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PedidoService {
    @Inject
    CarritoRepository carritoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ProductoRepository productoRepository; // para validar y sincronizar stock

    @Inject CatalogoService catalogoService; // usa lógica de descuento
}
