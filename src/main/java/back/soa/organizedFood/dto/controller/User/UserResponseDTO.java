package back.soa.organizedFood.dto.controller.User;

import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.controller.Recipe.RecipeResponseDTO;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Recipe;
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
public class UserResponseDTO {
    private String username;
    private List<HomeResponseDTO> homes = new ArrayList<>();
    private List<RecipeResponseDTO> recipes = new ArrayList<>();
}
