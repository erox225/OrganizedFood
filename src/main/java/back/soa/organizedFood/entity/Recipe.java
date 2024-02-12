package back.soa.organizedFood.entity;

import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.controller.Recipe.RecipeResponseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @Entity @AllArgsConstructor
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_product",
            joinColumns = @JoinColumn(name = "id_receta"),
            inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

    //Relaciones Inversas
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<User> users = new ArrayList<>();

    //Relaciones Inversas
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Home> homes = new ArrayList<>();

    public RecipeResponseDTO toDTO(boolean getHomesDT0, boolean getUsersDT0, boolean getProductsDTO) {
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setId(this.getId());
        recipeResponseDTO.setNombre(this.getNombre());
        recipeResponseDTO.setFechaCreacion(this.getFechaCreacion());
        if(getHomesDT0){
            this.getHomesDT0(recipeResponseDTO);
        }
        if(getUsersDT0){
            this.getUsersDT0(recipeResponseDTO);
        }
        if(getProductsDTO){
            this.getProductsDT0(recipeResponseDTO);
        }

        return recipeResponseDTO;
    }

    private void getHomesDT0(RecipeResponseDTO recipeResponseDTO){
        if(!this.getHomes().isEmpty()){
            for (Home home: this.getHomes()) {
                recipeResponseDTO.getHomes().add(home.toDTO(false,false));
            }
        }
    }
    private void getUsersDT0(RecipeResponseDTO recipeResponseDTO){
        if(!this.getUsers().isEmpty()){
            for(User user:this.getUsers()) {
                recipeResponseDTO.getUsers().add(user.toDTO(false,false));
            }
        }
    }
    private void getProductsDT0(RecipeResponseDTO recipeResponseDTO){
        if(!this.getProducts().isEmpty()){
            for(Product product:this.getProducts()) {
                recipeResponseDTO.getProducts().add(product.toDTO("1",false));
            }
        }
    }



   /* public RecipeRequestDTO toDTO(List<ProductRequestDTO> productRequestDTOS) {
        RecipeRequestDTO recetaDTO = new RecipeRequestDTO();
        recetaDTO.setId(String.valueOf(this.id));
        recetaDTO.setNombre(this.nombre);
        recetaDTO.setProductos(productRequestDTOS);
        return recetaDTO;
    }*/

}
