package back.soa.organizedFood.dto.controller.User;

import back.soa.organizedFood.dto.controller.Home.HomeRequestDTO;
import back.soa.organizedFood.dto.controller.Recipe.RecipeRequestDTO;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private Long id;
    private String username;
    private String password;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private List<HomeRequestDTO> homes;
    private List<RecipeRequestDTO> recipes;

    public User mapToModel() {

        List<Recipe> recipes = this.recipes.stream()
                .map(RecipeRequestDTO::mapToModel)
                .collect(Collectors.toList());

        List<Home> homes = this.homes.stream()
                .map(HomeRequestDTO::mapToModel)
                .collect(Collectors.toList());

        return new User(
                this.id,
                this.username,
                this.password,
                this.fechaCreacion,
                this.fechaModificacion,
                homes,
                recipes
        );
    }
}
