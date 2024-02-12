package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.Storage;
import back.soa.organizedFood.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Repository
public class RecipeDAO implements DAO {
    EntityManagerFactory enf = Persistence.createEntityManagerFactory("Organizedfood");
    EntityManager entityManager;

    public RecipeDAO(){
        this.entityManager = this.enf.createEntityManager();
    }

    @Override
    public Optional<Recipe> get(long id) {
        System.out.println("get en RecipeDAO with id = "+id);
        Optional<Recipe> result = Optional.ofNullable(entityManager.find(Recipe.class, id));
        return result;
    }

    @Override
    public Optional<List<Recipe>> getAll() {
        List<Recipe> allRecipes = entityManager.createQuery("FROM recipe", Recipe.class).getResultList();
        return Optional.ofNullable(allRecipes);
    }

    @Override
    public void save(Object o) {
        if (o instanceof Recipe) {
            Recipe recipe = (Recipe) o;
            entityManager.persist(recipe);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Recipe");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Recipe) {
            Recipe recipe = (Recipe) o;
            entityManager.merge(recipe);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Recipe");
        }
    }

    @Override
    public void delete(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            entityManager.remove(user);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo User");
        }
    }

    @Override
    public Optional<List<Recipe>> getAllInfoByUser(long idUser) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM recipe d WHERE d.user.id = :idUser", Recipe.class)
                .setParameter("idUser", idUser)
                .getResultList());
    }

    //Get Recipe by Home
    public Optional<List<Recipe>> getAllInfoByHome(long idHome) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM recipe d WHERE d.home.id = :idHome", Recipe.class)
                .setParameter("idHome", idHome)
                .getResultList());
    }
}
