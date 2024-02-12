package back.soa.organizedFood.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
@Getter @Setter @NoArgsConstructor @Entity
@Table(name = "product_status")
public class ProductStatus {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Color")
    private String color;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

}
