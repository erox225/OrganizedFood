package back.soa.organizedFood.controller;

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
        return new ResponseEntity<>(this.hogarService.getAllHomesInfoByUser(idUser), HttpStatus.OK);
    }

    @DeleteMapping("/homes/{id}")
    public ResponseEntity<?> deleteHome(@PathVariable String id){
        this.hogarService.
        DeleteServiceResponse response = this.pacomonService.delete(id);
        if (!response.isDeleted()){
            return new ResponseEntity<>(response.getValidationResult().createMsg(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PacomonResponseDTO>(HttpStatus.OK);
    }

}
