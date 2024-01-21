package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Repository
@Transactional
public class RecipeDAO implements DAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Recipe> get(long id) {
        System.out.println("get en RecipeDAO with id = "+id);
        Optional<Recipe> result = Optional.ofNullable(entityManager.find(Recipe.class, id));
        return result;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
