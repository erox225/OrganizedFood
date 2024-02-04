package back.soa.organizedFood.service;

import back.soa.organizedFood.dao.HomeDAO;
import back.soa.organizedFood.dto.services.*;
import back.soa.organizedFood.entity.*;
import back.soa.organizedFood.validations.ValidationResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HogarService {

    @Autowired
    private DespensaService despensaService;

    @Autowired
    private HomeDAO homeDAO;



    public DeleteServiceResponse delete(String id) {
        Boolean exist = this.findById(id).isPresent();
        if (exist){
            this.homeDAO.delete(id);
            return new DeleteServiceResponse(null,ValidationResultEnum.VALID_RESULT);
        }
        return new DeleteServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
    }

    public FindAllServiceResponse<Home> findAllByUserId(Long userId){
        Optional<List> result = this.homeDAO.getAllInfoByUser(userId);
        if(result.isEmpty()){
            return new FindAllServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindAllServiceResponse(result.get());
    }

    public FindOneServiceResponse<Home> findById(String id){
        Optional<Home> result = this.homeDAO.get(Long.parseLong(id));
        if(result.isEmpty()){
            return new FindOneServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult());
        }
        return new FindOneServiceResponse(result);
    }

    public UpdateServiceResponse update(Home home) {
        Boolean exist = this.findById(String.valueOf(home.getId())).isPresent();
        if (exist){
            this.homeDAO.update(home);
            return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
        }
        return new UpdateServiceResponse(ValidationResultEnum.VALID_RESULT.getValidationResult(),false);

    }

    public CreateServiceResponse<Home> add(Home home) {
        System.out.println("add en HomeDAO");
        return new CreateServiceResponse<>(ValidationResultEnum.VALID_RESULT.getValidationResult(),true);
    }


    public FindAllServiceResponse<Home> getAllByUserId(String idUser) {
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
            return new FindAllServiceResponse<>(new ArrayList<>(homesMap.values()));

        } catch (Exception e) {
            return new FindAllServiceResponse<>(ValidationResultEnum.VALID_RESULT);
        }

    }











    //Functions:

    // Verificar si la receta ya existe en la lista de recetas del hogar
    public Optional<Recipe> checkExistingReceta(Home homeEntity, Long idRecipe){
         return  homeEntity.getRecipes().stream()
                .filter(r -> r.getId().equals(idRecipe))
                .findFirst();
    }
    // Agregar producto a receta
    public void insertProductInRecipe(Recipe recipe, Product product){
        // Crear una nueva lista modificable para los productos
        List<Product> nuevosProductos = new ArrayList<>(recipe.getProducts());
        // Agregar el nuevo producto a la lista
        nuevosProductos.add(product);
        // Actualizar la lista de productos en la receta existente
        recipe.setProducts(nuevosProductos);
    }

    // Crear receta
    public Recipe createRecipeInHome(Storage storage){
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
