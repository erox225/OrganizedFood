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
        Optional<List<Storage>> result = this.storageDAO.getAllInfoByUser(idUser);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result.get());
    }

    public DeleteServiceResponse delete(String id) {
        System.out.println("delete en StorageService");
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.storageDAO.delete(id);
            return new DeleteServiceResponse(null, ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Storage> findAllByUserId(Long userId){
        System.out.println("findAllByUserId en StorageService");
        Optional<List<Storage>> result = this.storageDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result.get());
    }

    public FindOneServiceResponse<Storage> findById(String id){
        System.out.println("findById en StorageService");
        Optional<Storage> result = this.storageDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Storage storage) {
        System.out.println("Update en StorageService");
        //Comprobar que existe el objeto:
        boolean result = this.findById(String.valueOf(storage.getId())).isPresent();
        if(result){
            //Actualizar el objeto
            this.storageDAO.update(storage);
            return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
        }
        return new UpdateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),false);
    }

    public CreateServiceResponse<Storage> add(Home home) {
        System.out.println("add en StorageService");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }
}
