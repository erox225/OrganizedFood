package back.soa.organizedFood.dto.controller.Recipe;

import back.soa.organizedFood.dto.controller.Home.HomeRequestDTO;
import back.soa.organizedFood.dto.controller.Product.ProductRequestDTO;
import back.soa.organizedFood.dto.controller.User.UserRequestDTO;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequestDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private List<ProductRequestDTO> products;
    private List<UserRequestDTO> users;
    private List<HomeRequestDTO> homes;

    public Recipe mapToModel() {

        List<Product> products = this.products.stream()
                .map(ProductRequestDTO::mapToModel)
                .collect(Collectors.toList());

        List<User> users = this.users.stream()
                .map(UserRequestDTO::mapToModel)
                .collect(Collectors.toList());

        List<Home> homes = this.homes.stream()
                .map(HomeRequestDTO::mapToModel)
                .collect(Collectors.toList());

        return new Recipe(
                this.id,
                this.nombre,
                this.fechaCreacion,
                products,
                users,
                homes
        );
    }
}
