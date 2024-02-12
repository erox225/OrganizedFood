package back.soa.organizedFood.dto.controller.Recipe;

import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.controller.Product.ProductResponseDTO;
import back.soa.organizedFood.dto.controller.User.UserResponseDTO;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Product;
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
public class RecipeResponseDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private List<ProductResponseDTO> products = new ArrayList<>();
    private List<UserResponseDTO> users = new ArrayList<>();
    private List<HomeResponseDTO> homes = new ArrayList<>();
}
