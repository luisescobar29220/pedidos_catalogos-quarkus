package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrito")
public class CarritoModel {

    @Id
    @UuidGenerator
    public UUID id;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL,orphanRemoval = true)
    public List<LineaCarritoModel> lineas = new ArrayList<>();


    public CarritoModel() {
    }

    public UUID getId() { return id; }
    public List<LineaCarritoModel> getLineas() { return lineas; }
    public void setLineas(List<LineaCarritoModel> lineas) { this.lineas = lineas; }




}
