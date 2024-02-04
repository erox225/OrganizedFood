package back.soa.organizedFood.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//Entidad para registrar la compra que hace un usuario
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "valor")
    private float valor;
    //En curso : 1
    //Finalizado: 2
    @Column(name = "estado")
    private int estado;

    //Relacion con la tabla de Recipe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User user;

    //Relacion con la tabla de Home
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hogar")
    private Home home;
}
