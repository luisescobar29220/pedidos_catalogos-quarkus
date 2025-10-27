package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class PedidoModel {

    @Id
    @UuidGenerator
    private Long id;

    @Column(nullable = false)
    private OffsetDateTime fecha;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaPedidoModel> lineas = new ArrayList<>();

    public PedidoModel() {
    }

    public PedidoModel(OffsetDateTime fecha, BigDecimal total) {
        this.fecha = fecha;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<LineaPedidoModel> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoModel> lineas) {
        this.lineas = lineas;
    }
}

