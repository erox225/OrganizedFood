package back.soa.organizedFood.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    //Relaciones propietarias
    @ManyToMany
    @JoinTable(
            name = "recipe_product",
            joinColumns = @JoinColumn(name = "id_receta"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Product> products = new ArrayList<>();


    //Relaciones Inversas
    @ManyToMany(mappedBy = "recipes")
    private List<User> users = new ArrayList<>();

    //Relaciones Inversas
    @ManyToMany(mappedBy = "recipes")
    private List<Home> homes = new ArrayList<>();




   /* public RecipeRequestDTO toDTO(List<ProductRequestDTO> productRequestDTOS) {
        RecipeRequestDTO recetaDTO = new RecipeRequestDTO();
        recetaDTO.setId(String.valueOf(this.id));
        recetaDTO.setNombre(this.nombre);
        recetaDTO.setProductos(productRequestDTOS);
        return recetaDTO;
    }*/


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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }
}
