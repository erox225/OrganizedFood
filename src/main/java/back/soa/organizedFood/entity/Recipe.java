package back.soa.organizedFood.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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


}
