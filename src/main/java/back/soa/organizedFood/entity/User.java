package back.soa.organizedFood.entity;

import back.soa.organizedFood.dto.controller.Product.ProductResponseDTO;
import back.soa.organizedFood.dto.controller.User.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter @NoArgsConstructor @Entity @AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    //Relaciones propietarias
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "home_user",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_hogar"))
    @JsonBackReference
    private List<Home> homes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_user",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_receta"))
    @JsonBackReference
    private List<Recipe> recipes = new ArrayList<>();

    //Relaciones Inversas

    public UserResponseDTO toDTO(boolean getHomesDto, boolean getRecipesDTO) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(this.username);
        if(getHomesDto){
            this.getHomesDTO(userResponseDTO);
        }

        if(getRecipesDTO){
            this.getRecipesDTO(userResponseDTO);
        }

        return userResponseDTO;
    }

    private void getHomesDTO(UserResponseDTO userResponseDTO){
        if(!this.getHomes().isEmpty()){
            for (Home home: this.getHomes()) {
                userResponseDTO.getHomes().add(home.toDTO(false,false));
            }
        }
    }

    private void getRecipesDTO(UserResponseDTO userResponseDTO){
        if(!this.getRecipes().isEmpty()){
            for (Recipe recipe: this.getRecipes()){
                userResponseDTO.getRecipes().add(recipe.toDTO(false,false,false));
            }
        }
    }
}
