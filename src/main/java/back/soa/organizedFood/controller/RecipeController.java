package back.soa.organizedFood.controller;

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

    @GetMapping("/recipe/{idRecipe}")
    @ResponseBody
    public ResponseEntity<?> getRecipeById(@PathVariable Long idRecipe) {
        return new ResponseEntity<>(this.recetaService.getRecipeById(idRecipe), HttpStatus.OK);
    }

}
