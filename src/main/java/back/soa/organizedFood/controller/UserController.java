package back.soa.organizedFood.controller;

import back.soa.organizedFood.dto.controller.Home.HomeRequestDTO;
import back.soa.organizedFood.dto.controller.User.UserRequestDTO;
import back.soa.organizedFood.dto.services.CreateServiceResponse;
import back.soa.organizedFood.dto.services.DeleteServiceResponse;
import back.soa.organizedFood.dto.services.UpdateServiceResponse;
import back.soa.organizedFood.entity.User;
import back.soa.organizedFood.service.RecetaService;
import back.soa.organizedFood.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsuarioService userService;

    @GetMapping("/{idUser}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable Long idUser) {
        System.out.println("getUserById in UserController");
        return new ResponseEntity<>(this.userService.getUserById(idUser), HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO userRequestDTO, @PathVariable String id){
        System.out.println("updateUser in UserController");
        userRequestDTO.setId(Long.parseLong(id));
        UpdateServiceResponse response = this.userService.update(userRequestDTO.mapToModel());
        if(!response.isUpdated()){
            return new ResponseEntity<>("No se ha podido actualizar"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Actualizado correctamente"
                , HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        System.out.println("deleteUser in UserController");
        DeleteServiceResponse response = this.userService.delete(id);
        if (!response.isDeleted()){
            return new ResponseEntity<>("No se ha eliminado"
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Se ha eliminado"
                , HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        System.out.println("createUser in UserController");
        CreateServiceResponse<User> response = this.userService.add(userRequestDTO);

        if (!response.isCreated()) {
            return new ResponseEntity<>("No se ha podido crear"
                    , HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Se ha creado Correctamente"
                , HttpStatus.CREATED);
    }
}
