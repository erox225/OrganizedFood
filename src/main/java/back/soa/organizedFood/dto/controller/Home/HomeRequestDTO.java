package back.soa.organizedFood.dto.controller.Home;

import back.soa.organizedFood.dto.controller.Recipe.RecipeRequestDTO;
import back.soa.organizedFood.dto.controller.User.UserRequestDTO;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class HomeRequestDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private List<RecipeRequestDTO> recipes;
    private List<UserRequestDTO> users;

    public Home mapToModel() {
        List<Recipe> recipes = this.recipes.stream()
                .map(RecipeRequestDTO::mapToModel)
                .collect(Collectors.toList());

        List<User> users = this.users.stream()
                .map(UserRequestDTO::mapToModel)
                .collect(Collectors.toList());

        return new Home(
                this.id,
                this.nombre,
                recipes,
                users
        );
    }
}
