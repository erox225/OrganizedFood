package back.soa.organizedFood.service;

import back.soa.organizedFood.dto.dashboard.GetAllInfoDespensaResponseDTO;
import back.soa.organizedFood.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HogarService {

    @Autowired
    private DespensaService despensaService;

    public List<Home> getAllHomesInfoByUser(String idUser) {
        GetAllInfoDespensaResponseDTO getAllInfoDespensaResponseDTO = new GetAllInfoDespensaResponseDTO();
        getAllInfoDespensaResponseDTO.setResult("ok");
        getAllInfoDespensaResponseDTO.setDescription("successful");

        List<Home> homeEntityList = new ArrayList<>();

        try {
            List<Storage> getAllDashboardInfoByHogarResponse = despensaService.getAllInfoByUser(idUser);

            // Agrupar las recetas y productos por hogar
            Map<Long, Home> hogaresMap = new HashMap<>();
            for (Storage storage : getAllDashboardInfoByHogarResponse) {
                Long hogarId = storage.getHome().getId();

                //Creamos el hogar
                Home homeEntity = hogaresMap.getOrDefault(hogarId, new Home());
                homeEntity.setId(hogarId);
                homeEntity.setNombre(storage.getHome().getNombre());

                // Verificar si la receta ya existe en la lista de recetas del hogar
                Optional<Recipe> existingReceta = homeEntity.getRecipes().stream()
                        .filter(r -> r.getId().equals(String.valueOf(storage.getRecipe().getId())))
                        .findFirst();

                if (existingReceta.isPresent()) {

                    Product product = storage.getProduct();

                    // La receta ya existe, agregar el producto
                    Recipe receta = existingReceta.get();

                    // Crear una nueva lista modificable para los productos
                    List<Product> nuevosProductos = new ArrayList<>(receta.getProducts());

                    // Agregar el nuevo producto a la lista
                    nuevosProductos.add(product);

                    // Actualizar la lista de productos en la receta existente
                    receta.setProducts(nuevosProductos);

                }else{
                    //Tenemos que crear la receta en el Hogar y le agregamos el producto que necesita

                    //Obtenemos el Estado del producto
                    ProductStatus productStatus = storage.getProductStatus();

                    //Obtenemos el Producto
                    Product product = storage.getProduct();
                    product.setEstadoProducto(productStatus.getNombre());

                    //Creamos la lista de productos
                    List<Product> productsEntityList = Arrays.asList(product);

                    //Obtenemos la receta
                    Recipe recipe = storage.getRecipe();

                    //Seteamos la lista en la receta
                    recipe.setProducts(productsEntityList);

                    //Agregamos la receta al Hogar
                    homeEntity.getRecipes().add(recipe);
                }

                hogaresMap.put(hogarId, homeEntity);
            }

            //Agregamos un Hogar a la lista
            homeEntityList.addAll(hogaresMap.values());
        } catch (Exception e) {
            // Manejar la excepción general
            //logger.error("Error en getAllInfoDespensa: " + e.getMessage());
            e.printStackTrace();
            getAllInfoDespensaResponseDTO.setResult("error");
            getAllInfoDespensaResponseDTO.setDescription("Ha ocurrido un error en el servicio.");
        }
        return homeEntityList;
    }

}
