package back.soa.organizedFood.entity;

import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.controller.Recipe.RecipeResponseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @Entity @AllArgsConstructor
@Table(name = "home")
public class Home {

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
            name = "recipe_home",
            joinColumns = @JoinColumn(name = "id_receta"),
            inverseJoinColumns = @JoinColumn(name = "id_hogar"))
    @JsonManagedReference
    private List<Recipe> recipes = new ArrayList<>();

    //Relaciones Inversas
    @ManyToMany(mappedBy = "homes", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<User> users = new ArrayList<>();

    public Home(Long id, String nombre, List<Recipe> recipes, List<User> users) {
        this.id = id;
        this.nombre = nombre;
        this.recipes = recipes;
        this.users = users;
    }

    public HomeResponseDTO toDTO(boolean getRecipesDTO,boolean getUsersDTO) {
        HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
        homeResponseDTO.setId(this.getId());
        homeResponseDTO.setNombre(this.getNombre());
        homeResponseDTO.setFechaCreacion(this.getFechaCreacion());
        if(getRecipesDTO){
            this.getRecipesDTO(homeResponseDTO);
        }
        if(getUsersDTO){
            this.getUsersDTO(homeResponseDTO);
        }
        return homeResponseDTO;
    }

    private void getRecipesDTO(HomeResponseDTO homeResponseDTO){
        if(!this.getRecipes().isEmpty()){
            for (Recipe recipe: this.getRecipes()) {
                homeResponseDTO.getRecipes().add(recipe.toDTO(false,false,true));
            }
        }
    }

    private void getUsersDTO(HomeResponseDTO homeResponseDTO){
        if(!this.getUsers().isEmpty()){
            for(User user:this.getUsers()) {
                homeResponseDTO.getUsers().add(user.toDTO(false,false));
            }
        }
    }
}
