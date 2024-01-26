package back.soa.organizedFood.service;

import back.soa.organizedFood.OrganizedFoodApplication;
import back.soa.organizedFood.dao.StorageDAO;
import back.soa.organizedFood.dao.HomeDAO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.*;
import back.soa.organizedFood.validations.ValidationResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@Service
public class DespensaService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizedFoodApplication.class);
    @Autowired
    private StorageDAO storageDAO;

    public FindAllServiceResponse<Storage> getAllInfoByUser(long idUser) {
        System.out.println("getAllInfoByUser en StorageService with idUser = "+idUser);
        List<Storage> result = this.storageDAO.getAllInfoByUser(idUser);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result);
    }

    public DeleteServiceResponse delete(String id) {
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.storageDAO.delete(id);
            return new DeleteServiceResponse(null, ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Storage> findAllByUserId(Long userId){
        List<Storage> result = this.storageDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result);
    }

    public FindOneServiceResponse<Storage> findById(String id){
        Optional<Storage> result = this.storageDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Storage storage) {
        return new UpdateServiceResponse<>(this.storageDAO.update(storage));
    }

    public CreateServiceResponse<Storage> add(Home home) {
        System.out.println("add en StorageService");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }
}
