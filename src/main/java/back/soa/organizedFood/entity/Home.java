package back.soa.organizedFood.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
