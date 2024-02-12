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
        System.out.println("delete in ProductoService");
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.productDAO.delete(id);
            return new DeleteServiceResponse(null, ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Product> findAllByUserId(Long userId){
        System.out.println("findAllByUserId in ProductoService");
        Optional<List<Product>> result = this.productDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result.get());
    }

    public FindOneServiceResponse<Product> findById(String id){
        System.out.println("findById in ProductoService");
        Optional<Product> result = this.productDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Product product) {
        System.out.println("update in ProductoService");
        Boolean exist = this.findById(String.valueOf(product.getId())).isPresent();
        if (exist){
            this.productDAO.update(product);
            return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
        }
        return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),false);
    }

    public CreateServiceResponse<Product> add(Product product) {
        System.out.println("add in ProductoService");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }

    public Object getProductsByIdHome(Long idHome) {
        System.out.println("getProductsByIdHome in ProductoService");
    }

    public Object getProductsByRecipeId(Long idRecipe) {
        System.out.println("getProductsByRecipeId in ProductoService");
    }

    public Object getProducts(Long idProduct) {
        System.out.println("getProducts in ProductoService");
    }
}
