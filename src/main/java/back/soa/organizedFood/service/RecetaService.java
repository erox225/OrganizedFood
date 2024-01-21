package back.soa.organizedFood.service;

import back.soa.organizedFood.dao.RecipeDAO;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecipeDAO recipeDAO;
    public Optional<Recipe> getRecipeById(Long idRecipe) {
        Optional<Recipe> result = this.recipeDAO.get(idRecipe);
        return result;
    }
}
