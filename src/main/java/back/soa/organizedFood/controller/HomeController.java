package back.soa.organizedFood.controller;

import back.soa.organizedFood.dto.controller.Home.HomeRequestDTO;
import back.soa.organizedFood.dto.controller.Home.HomeResponseDTO;
import back.soa.organizedFood.dto.services.CreateServiceResponse;
import back.soa.organizedFood.dto.services.DeleteServiceResponse;
import back.soa.organizedFood.dto.services.UpdateServiceResponse;
import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.service.HogarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private HogarService hogarService;

    @GetMapping("/user/{idUser}/homes/recipes/products")
    @ResponseBody
    public ResponseEntity<?> getAllInfoHomeByUser(@PathVariable(name = "idUser", required = true) String idUser) {
        System.out.println("getAllInfoHomeByUser in HomeController");
        return new ResponseEntity<>(this.hogarService.getAllByUserId(idUser)
                , HttpStatus.OK);
    }

    @GetMapping("/homes/{idHome}")
    @ResponseBody
    public ResponseEntity<?> getHomeById(@PathVariable String idRecipe) {
        System.out.println("getHomeById in HomeController");
        return new ResponseEntity<>(this.hogarService.getRecipeById(idRecipe)
                , HttpStatus.OK);
    }

    @GetMapping("users/{idUser}/homes")
    @ResponseBody
    public ResponseEntity<?> getHomesByUserId(@PathVariable String idUser) {
        System.out.println("getHomesByUserId in HomeController");
        return new ResponseEntity<>(this.hogarService.getHomesByUserId(idUser), HttpStatus.OK);
    }

    @PatchMapping("/homes/{id}")
    public ResponseEntity<?> updateHome(@RequestBody HomeRequestDTO homeRequestDTO, @PathVariable String id){
        System.out.println("updateHome in HomeController");
        homeRequestDTO.setId(Long.parseLong(id));
        UpdateServiceResponse response = this.hogarService.update(homeRequestDTO.mapToModel());
        if(!response.isUpdated()){
            return new ResponseEntity<>("No se ha podido actualizar"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Actualizado correctamente"
                , HttpStatus.OK);
    }

    @DeleteMapping("/homes/{id}")
    public ResponseEntity<?> deleteHome(@PathVariable String id){
        System.out.println("deleteHome in HomeController");
        DeleteServiceResponse response = this.hogarService.delete(id);
        if (!response.isDeleted()){
            return new ResponseEntity<>("No se ha eliminado"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Se ha eliminado"
                , HttpStatus.OK);
    }

    @PostMapping("/homes")
    public ResponseEntity<?> createHome(@RequestBody HomeRequestDTO homeRequestDTO) {
        System.out.println("createHome in HomeController");
        CreateServiceResponse<Home> response = this.hogarService.add(homeRequestDTO.mapToModel());

        if (!response.isCreated()) {
            return new ResponseEntity<>("No se ha podido crear"
                    , HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Se ha creado Correctamente"
                , HttpStatus.CREATED);
    }

}
