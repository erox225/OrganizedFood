package back.soa.organizedFood.controller;

import back.soa.organizedFood.dto.controller.Home.HomeRequestDTO;
import back.soa.organizedFood.dto.controller.Recipe.RecipeRequestDTO;
import back.soa.organizedFood.dto.services.CreateServiceResponse;
import back.soa.organizedFood.dto.services.DeleteServiceResponse;
import back.soa.organizedFood.dto.services.UpdateServiceResponse;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.service.HogarService;
import back.soa.organizedFood.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping("/recipes/{idRecipe}")
    @ResponseBody
    public ResponseEntity<?> getRecipeById(@PathVariable Long idRecipe) {
        System.out.println("getRecipeById in RecipeController");
        return new ResponseEntity<>(this.recetaService.getRecipeById(idRecipe), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/recipes/")
    @ResponseBody
    public ResponseEntity<?> getRecipeByUserId(@PathVariable Long userId) {
        System.out.println("getRecipeByUserId in RecipeController");
        return new ResponseEntity<>(this.recetaService.getRecipeByUserId(userId), HttpStatus.OK);
    }

    @PatchMapping("/recipes/{id}")
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeRequestDTO recipeRequestDTO, @PathVariable String id){
        System.out.println("updateRecipe in RecipeController");
        recipeRequestDTO.setId(Long.parseLong(id));
        UpdateServiceResponse response = this.recetaService.update(recipeRequestDTO.mapToModel());
        if(!response.isUpdated()){
            return new ResponseEntity<>("No se ha podido actualizar"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Actualizado correctamente"
                , HttpStatus.OK);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable String id){
        System.out.println("deleteRecipe in RecipeController");
        DeleteServiceResponse response = this.recetaService.delete(id);
        if (!response.isDeleted()){
            return new ResponseEntity<>("No se ha eliminado"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Se ha eliminado"
                , HttpStatus.OK);
    }

    @PostMapping("/recipes")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeRequestDTO recipeRequestDTO) {
        System.out.println("createRecipe in RecipeController");
        CreateServiceResponse<Recipe> response = this.recetaService.add(recipeRequestDTO);

        if (!response.isCreated()) {
            return new ResponseEntity<>("No se ha podido crear"
                    , HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Se ha creado Correctamente"
                , HttpStatus.CREATED);
    }

}
