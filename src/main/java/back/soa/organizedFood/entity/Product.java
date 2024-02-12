package back.soa.organizedFood.entity;

import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.controller.Product.ProductResponseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @Entity @AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    //Relaciones propietarias

    //Relaciones Inversas
    @ManyToMany(mappedBy = "products",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Recipe> recipes = new ArrayList<>();

    public Product(long id, String nombre, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public ProductResponseDTO toDTO(String estado,boolean getRecipesDT0) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(this.id);
        productResponseDTO.setNombre(this.nombre);
        productResponseDTO.setProductStatus(estado);
        if(getRecipesDT0){
            this.getRecipesDT0(productResponseDTO);
        }
        return productResponseDTO;
    }

    private void getRecipesDT0(ProductResponseDTO productResponseDTO){
        if(!this.getRecipes().isEmpty()){
            for (Recipe recipe: this.getRecipes()) {
                productResponseDTO.getRecipes().add(recipe.toDTO(false,false,false));
            }
        }
    }
}
