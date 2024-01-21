package back.soa.organizedFood.service;

import back.soa.organizedFood.OrganizedFoodApplication;
import back.soa.organizedFood.dao.StorageDAO;
import back.soa.organizedFood.dao.HomeDAO;
import back.soa.organizedFood.entity.*;
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

    @Autowired
    private HomeDAO homeDAO;


    public List<Storage> getAllInfoByUser(String idUser) {
        System.out.println("getAllInfoByUser en DespensaService with idUser = "+idUser);
        List<Storage> variable = this.storageDAO.getAllInfoByUser(idUser);
        return variable;
    }
}
