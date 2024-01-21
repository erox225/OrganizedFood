package back.soa.organizedFood.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "home")
public class Home {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    //Relaciones propietarias
    @ManyToMany
    @JoinTable(
            name = "recipe_home",
            joinColumns = @JoinColumn(name = "id_receta"),
            inverseJoinColumns = @JoinColumn(name = "id_hogar"))
    private List<Recipe> recipes = new ArrayList<>();

    //Relaciones Inversas
    @ManyToMany(mappedBy = "homes")
    private List<User> users = new ArrayList<>();

    /*
    public HomeRequestDTO toDTO() {
        HomeRequestDTO homeRequestDTO = new HomeRequestDTO();
        homeRequestDTO.setId(String.valueOf(this.id));
        homeRequestDTO.setNombreHogar(this.getNombre());
        //hogarDTO.setRecetas(recetaDTOS);
        return homeRequestDTO;
    }
    */

}
