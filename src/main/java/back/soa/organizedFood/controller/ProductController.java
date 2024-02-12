package back.soa.organizedFood.controller;

import back.soa.organizedFood.dto.controller.Product.ProductRequestDTO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity<?> getProducts(@PathVariable Long idProduct) {
        System.out.println("getProducts in ProductController");
        return new ResponseEntity<>(this.productoService.getProducts(idProduct), HttpStatus.OK);
    }

    @GetMapping("recipes/{idRecipe}/products")
    @ResponseBody
    public ResponseEntity<?> getProductsByIdRecipe(@PathVariable Long idRecipe) {
        System.out.println("getProductsByIdRecipe in ProductController");
        return new ResponseEntity<>(this.productoService.getProductsByRecipeId(idRecipe), HttpStatus.OK);
    }

    @GetMapping("homes/{idHome}/products")
    @ResponseBody
    public ResponseEntity<?> getProductsByIdHome(@PathVariable Long idHome) {
        System.out.println("getProductsByIdHome in ProductController");
        return new ResponseEntity<>(this.productoService.getProductsByIdHome(idHome), HttpStatus.OK);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable String id){
        System.out.println("updateProduct in ProductController");
        productRequestDTO.setId(Long.parseLong(id));
        UpdateServiceResponse response = this.productoService.update(productRequestDTO.mapToModel());
        if(!response.isUpdated()){
            return new ResponseEntity<>("No se ha podido actualizar"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Actualizado correctamente"
                , HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        System.out.println("deleteProduct in ProductController");
        DeleteServiceResponse response = this.productoService.delete(id);
        if (!response.isDeleted()){
            return new ResponseEntity<>("No se ha eliminado"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Se ha eliminado"
                , HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        System.out.println("createProduct in ProductController");
        CreateServiceResponse<Product> response = this.productoService.add(productRequestDTO.mapToModel());

        if (!response.isCreated()) {
            return new ResponseEntity<>("No se ha podido crear"
                    , HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Se ha creado Correctamente"
                , HttpStatus.CREATED);
    }

}
