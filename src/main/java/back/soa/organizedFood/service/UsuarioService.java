package back.soa.organizedFood.service;

import back.soa.organizedFood.dao.RecipeDAO;
import back.soa.organizedFood.dao.UserDAO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.User;
import back.soa.organizedFood.validations.ValidationResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UserDAO userDAO;
    public DeleteServiceResponse delete(String id) {
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.userDAO.delete(id);
            return new DeleteServiceResponse(null, ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindOneServiceResponse<User> findById(String id){
        Optional<User> result = this.userDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

   /* public UpdateServiceResponse update(User user) {
        return new UpdateServiceResponse<>(this.userDAO.update(user));
    }*/

    public CreateServiceResponse<User> add(User user) {
        System.out.println("add en HomeDAO");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }
}
