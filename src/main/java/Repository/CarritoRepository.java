package Repository;

import Model.CarritoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class CarritoRepository implements PanacheRepositoryBase<CarritoModel, Long> {

}
