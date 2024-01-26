package back.soa.organizedFood.service;

import back.soa.organizedFood.dao.RecipeDAO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.validations.ValidationResultEnum;
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
    public DeleteServiceResponse delete(String id) {
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.recipeDAO.delete(id);
            return new DeleteServiceResponse(null, ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Recipe> findAllByUserId(Long userId){
        List<Recipe> result = this.recipeDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result);
    }

    public FindOneServiceResponse<Recipe> findById(String id){
        Optional<Recipe> result = this.recipeDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Recipe recipe) {
        return new UpdateServiceResponse<>(this.recipeDAO.update(recipe));
    }

    public CreateServiceResponse<Recipe> add(Recipe recipe) {
        System.out.println("add en HomeDAO");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }

}
