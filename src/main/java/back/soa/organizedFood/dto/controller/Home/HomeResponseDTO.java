package back.soa.organizedFood.dto.controller.Home;

import back.soa.organizedFood.dto.controller.Recipe.RecipeResponseDTO;
import back.soa.organizedFood.dto.controller.User.UserResponseDTO;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HomeResponseDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private List<RecipeResponseDTO> recipes = new ArrayList<>();
    private List<UserResponseDTO> users = new ArrayList<>();
}
