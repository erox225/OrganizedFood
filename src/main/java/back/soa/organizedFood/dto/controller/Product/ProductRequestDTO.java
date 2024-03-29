package back.soa.organizedFood.dto.controller.Product;

import back.soa.organizedFood.dto.controller.Recipe.RecipeRequestDTO;
import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private List<RecipeRequestDTO> recipes;

    public Product mapToModel() {

        List<Recipe> recipes = this.recipes.stream()
                .map(RecipeRequestDTO::mapToModel)
                .collect(Collectors.toList());

        return new Product(
                this.id,
                this.nombre,
                this.fechaCreacion,
                recipes
        );
    }
}
