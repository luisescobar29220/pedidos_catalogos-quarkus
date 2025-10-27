package Service;

import Model.ProductoModel;
import Repository.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CatalogoService {

    @Inject
    ProductoRepository productoRepository;

    public List<ProductoModel> listar(){
        return productoRepository.listAll();
    }

    public Optional<ProductoModel> porId(Long id){
        return  productoRepository.findByIdOptional(id);
    }

    @Transactional
    public ProductoModel crear(String sku, String nombre, int stock, int stockMinimo, BigDecimal precio,String moneda){
        var p = new ProductoModel(sku,nombre,stock,stockMinimo,precio,moneda);
        productoRepository.persist(p);
        return p;
    }

    @Transactional
    public void descontarStock(Long productoId, int cantidad){
        var p = productoRepository.findById(productoId);
        if(p == null) throw new IllegalStateException("producto no existe");
        int nuevo = p.getStock() -cantidad;
        if (nuevo < p.getStockMinimo()){
            throw new IllegalStateException("Stock insuficiente respetando stock mínimo");
        }
        p.setStock(nuevo);
    }


}







