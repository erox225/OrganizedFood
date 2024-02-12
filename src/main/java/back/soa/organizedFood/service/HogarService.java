package back.soa.organizedFood.service;

import back.soa.organizedFood.dao.HomeDAO;
import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.*;
import back.soa.organizedFood.validations.ValidationResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class HogarService {

    private static final Logger logger = LoggerFactory.getLogger(HogarService.class);

    @Autowired
    private DespensaService despensaService;

    @Autowired
    private HomeDAO homeDAO;

    ////////////////////////////////Public Functions:
    public DeleteServiceResponse delete(String id) {
        System.out.println("delete in HogarService");
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.homeDAO.delete(id);
            return new DeleteServiceResponse(null,ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Home> findAllByUserId(Long userId){
        System.out.println("findAllByUserId in HogarService");
        Optional<List> result = this.homeDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result.get());
    }

    public FindAllServiceResponse<Home> findAll(Long userId){
        System.out.println("findAll in HogarService");
        Optional<List> result = this.homeDAO.getAllInfo(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result.get());
    }

    public FindOneServiceResponse<Home> findById(String id){
        System.out.println("findById in HogarService");
        Optional<Home> result = this.homeDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Home home) {
        System.out.println("update in HogarService");
        Boolean exist = this.findById(String.valueOf(home.getId())).isPresent();
        if (exist){
            this.homeDAO.update(home);
            return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
        }
        return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),false);

    }

    public CreateServiceResponse<Home> add(Home home) {
        System.out.println("add in HogarService");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }


    public FindAllServiceResponse<HomeResponseDTO> getAllByUserId(String idUser) {
        System.out.println("getAllByUserId in HogarService");
        try {
            FindAllServiceResponse<Storage> getAllDashboardInfoByHogarResponse = despensaService.getAllInfoByUser(Long.parseLong(idUser));
            if(getAllDashboardInfoByHogarResponse.getPayload().isEmpty()){
                return new FindAllServiceResponse<>(ValidationResultEnum.VALID_RESULT);
            }

            // Agrupar las recetas y productos por hogar
            Map<Long, Home> homesMap = new HashMap<>();
            for (Storage storage : getAllDashboardInfoByHogarResponse.getPayload()) {
                //Obtenemos el hogar
                Home home = homesMap.getOrDefault(storage.getHome().getId(), new Home());
                home.setId(storage.getHome().getId());
                home.setNombre(storage.getHome().getNombre());

                Optional<Recipe> existingRecipe = this.checkExistingReceta(home,storage.getRecipe().getId());
                if (existingRecipe.isPresent()) {
                    this.insertProductInRecipe(existingRecipe.get(),storage.getProduct());
                }else{
                    home.getRecipes().add(this.createRecipeInHome(storage));
                }
                homesMap.put(storage.getHome().getId(), home);
            }

            //logger.info("Estamos aqui {}", homesMap.values());
            Map<Integer, HomeResponseDTO> homesMapDTO = new HashMap<>();
            for(int i=1;i<=homesMap.size();i++){
                HomeResponseDTO variables = homesMap.get(Long.parseLong(String.valueOf(i))).toDTO(true,false);
                homesMapDTO.put(i,variables);
            }

            return new FindAllServiceResponse<>(new ArrayList<>(homesMapDTO.values()));

        } catch (Exception e) {
            return new FindAllServiceResponse<>(ValidationResultEnum.VALID_RESULT);
        }

    }

    public Object getRecipeById(String idRecipe) {
        System.out.println("getRecipeById in HogarService");
    }

    public Object getHomesByUserId(String idUser) {
        System.out.println("getHomesByUserId in HogarService");
    }

    ////////////////////////////////Private Functions:
    // Verificar si la receta ya existe en la lista de recetas del hogar
    public Optional<Recipe> checkExistingReceta(Home homeEntity, Long idRecipe){
        System.out.println("checkExistingReceta in HogarService");
         return  homeEntity.getRecipes().stream()
                .filter(r -> r.getId().equals(idRecipe))
                .findFirst();
    }
    // Agregar producto a receta
    private void insertProductInRecipe(Recipe recipe, Product product){
        System.out.println("insertProductInRecipe in HogarService");
        // Crear una nueva lista modificable para los productos
        List<Product> nuevosProductos = new ArrayList<>(recipe.getProducts());
        // Agregar el nuevo producto a la lista
        nuevosProductos.add(product);
        // Actualizar la lista de productos en la receta existente
        recipe.setProducts(nuevosProductos);
    }

    // Crear receta
    private Recipe createRecipeInHome(Storage storage){
        System.out.println("createRecipeInHome in HogarService");
        //Obtenemos el Estado del producto
        ProductStatus productStatus = storage.getProductStatus();
        //Obtenemos el Producto
        Product product = storage.getProduct();
        //product.setEstadoProducto(productStatus.getNombre());
        //Creamos la lista de productos
        List<Product> productsEntityList = Arrays.asList(product);
        //Obtenemos la receta
        Recipe recipe = storage.getRecipe();
        //Seteamos la lista en la receta
        recipe.setProducts(productsEntityList);
        //Agregamos la receta al Hogar
        return recipe;
    }

}
