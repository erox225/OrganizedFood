package back.soa.organizedFood.controller;

import back.soa.organizedFood.dto.dashboard.GetAllInfoDespensaRequestDTO;
import back.soa.organizedFood.dto.dashboard.GetAllInfoDespensaResponseDTO;
import back.soa.organizedFood.service.DespensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/despensa")
public class StorageRoomController {

    @Autowired
    private DespensaService despensaService;

    /*@GetMapping("/getAllInfoDespensa")
    @ResponseBody
    public GetAllInfoDespensaResponseDTO getAllInfoDespensa(@ModelAttribute GetAllInfoDespensaRequestDTO getAllInfoDespensaRequestDTO) {
        //Long idUser = getAllInfoDespensaRequestDTO.getIdUser();
        //return despensaService.getAllInfoDespensa(idUser);
    }

     */

    @GetMapping("/saludo")
    @ResponseBody
    public String saludo() {
        String Hola = "hola";
        return "Hola7";
    }

}
