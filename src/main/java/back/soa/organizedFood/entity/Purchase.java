package back.soa.organizedFood.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Table(name = "purchase")
public class Purchase {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    //En curso : 1
    //Finalizado: 2
    @Column(name = "estado")
    private int estado;

}
