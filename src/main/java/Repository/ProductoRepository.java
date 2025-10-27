package Repository;

import Model.ProductoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProductoRepository implements PanacheRepositoryBase<ProductoModel , Long> {

    public Optional<ProductoModel> findBySku(String sku) {
        return find("sku", sku).firstResultOptional();
    }
}
