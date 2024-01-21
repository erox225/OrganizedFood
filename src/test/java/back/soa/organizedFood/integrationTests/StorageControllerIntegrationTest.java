package back.soa.organizedFood.integrationTests;

import back.soa.organizedFood.dao.StorageDAO;
import back.soa.organizedFood.dto.dashboard.GetAllInfoDespensaRequestDTO;
import back.soa.organizedFood.dto.dashboard.GetAllInfoDespensaResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StorageControllerIntegrationTest {
    private static final String URL_LOCAL_HOST ="";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StorageDAO storageDAO;

    @LocalServerPort
    private int port;

    @Test
    public void getAllInfoDespensaOK() {
        // Preparar el objeto DTO para la solicitud
        GetAllInfoDespensaRequestDTO requestDTO = new GetAllInfoDespensaRequestDTO();
        requestDTO.setIdUser(1L); // Ajusta el valor según tus necesidades

        // Realizar la llamada a la API
        ResponseEntity<GetAllInfoDespensaResponseDTO> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/organizedFood/api/despensa/getAllInfoDespensa?idUser={idUser}",
                GetAllInfoDespensaResponseDTO.class,
                requestDTO.getIdUser());

        // Verificación de la respuesta HTTP
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verificación de los datos en la respuesta
        GetAllInfoDespensaResponseDTO responseBody = responseEntity.getBody();
        assertNotNull(responseBody);

        // Verificacion de datos sobre las propiedades del JSON
        assertEquals("ok", responseBody.getResult());
        assertEquals("successful", responseBody.getDescription());
        assertNotNull(responseBody.getHogares());
        assertFalse(responseBody.getHogares().isEmpty());
    }

    /*
    @Test
    public void getAllInfoDespensaBadRequestParams() {

        // Preparar el objeto DTO para la solicitud
        GetAllInfoDespensaRequestDTO requestDTO = new GetAllInfoDespensaRequestDTO();
        requestDTO.setIdUser(null); // Ajusta el valor segun tus necesidades

        // Realizar la llamada a la API
        ResponseEntity<GetAllInfoDespensaResponseDTO> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/organizedFood/api/despensa/getAllInfoDespensa?idUser={idUser}",
                GetAllInfoDespensaResponseDTO.class,
                requestDTO.getIdUser());

        // Verificación de la respuesta HTTP para solicitud incorrecta
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
     */

}
