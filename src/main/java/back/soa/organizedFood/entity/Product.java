package back.soa.organizedFood.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    //Relaciones propietarias

    //Relaciones Inversas
    @ManyToMany(mappedBy = "products")
    private List<Recipe> recipes = new ArrayList<>();

    public Product(long id, String nombre, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

/*
    public ProductRequestDTO toDTO(String estado) {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setId(String.valueOf(this.id));
        productRequestDTO.setNombre(this.nombre);
        productRequestDTO.setEstado(estado);
        return productRequestDTO;
    }
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
