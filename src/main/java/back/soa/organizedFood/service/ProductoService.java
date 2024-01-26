package back.soa.organizedFood.service;

import back.soa.organizedFood.dao.ProductDAO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.validations.ValidationResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductDAO productDAO;

    public DeleteServiceResponse delete(String id) {
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.productDAO.delete(id);
            return new DeleteServiceResponse(null, ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Product> findAllByUserId(Long userId){
        List<Product> result = this.productDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result);
    }

    public FindOneServiceResponse<Product> findById(String id){
        Optional<Product> result = this.productDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Product product) {
        return new UpdateServiceResponse<>(this.productDAO.update(product));
    }

    public CreateServiceResponse<Product> add(Product product) {
        System.out.println("add en HomeDAO");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }
}
