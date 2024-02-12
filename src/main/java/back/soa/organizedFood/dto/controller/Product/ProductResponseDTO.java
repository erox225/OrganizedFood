package back.soa.organizedFood.dto.controller.Product;

import back.soa.organizedFood.dto.controller.Recipe.RecipeResponseDTO;
import back.soa.organizedFood.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String nombre;
    private String productStatus;
    private List<RecipeResponseDTO> recipes = new ArrayList<>();
}
