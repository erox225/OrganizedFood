package back.soa.organizedFood.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Getter @Setter @NoArgsConstructor @Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    //Relaciones

    //Relacion con la tabla de Recipe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta")
    private Recipe recipe;

    //Relacion con la tabla de Home
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hogar")
    private Home home;

    //Relacion con la tabla de Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Product product;

    //Relacion con la tabla del Estado del  Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_producto")
    private ProductStatus productStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    //Relacion con la tabla de la Compra

}
