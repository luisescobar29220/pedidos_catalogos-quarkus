package Repository;

import Model.PedidoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<PedidoModel, Long> {
}
