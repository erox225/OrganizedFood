package back.soa.organizedFood.unitsTests;

import back.soa.organizedFood.dao.StorageDAO;
import back.soa.organizedFood.entity.*;
import back.soa.organizedFood.service.DespensaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceUnitsTest {

    @Autowired
    private DespensaService despensaService;

    @MockBean
    private StorageDAO storageDAO;

    @Test
    public void testObtenerTodos() {
        /*
        Configuracion de objetos para mockear la llamada a la capa de datos:
        Long id;
        Date fechaCreacion;
        Receta receta;
        Hogar hogar;
        Producto producto;
        EstadoProducto estadoProducto;
        */
/*
        Date fechaActual = new Date(System.currentTimeMillis());
        ProductStatus productStatus = new ProductStatus(1L, "Disponible", "Verde", fechaActual);
        Product product = new Product(1L,"Pollo",fechaActual,null);
        Recipe recipe = new Recipe(1L,"Arroz Con pollo",fechaActual,null,null,null);
        Home home = new Home(1L,"Hogar",fechaActual,null,null);
        Storage storage = new Storage(1L,fechaActual, recipe, home, product, productStatus);


        // Configuracion del mock, este mock se utiliza para simular el comportamiento de la capa DAO, basicamente
        //Se esta comprobando que la capa de servicio funciona correctamente
        List<Storage> storageEntities = new ArrayList<>();
        storageEntities.add(storage);
        Mockito.when(despensaService.getAllInfoByUser("1")).thenReturn(storageEntities);

        // Llamada al servicio
        List<Storage> getAllInfoDespensaResponseDTO = despensaService.getAllInfoByUser("1");

        // Verificación


        assertNotNull(getAllInfoDespensaResponseDTO);
         */
    }
}
