package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.entity.Recipe;
import back.soa.organizedFood.entity.Storage;
import back.soa.organizedFood.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDAO implements DAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional get(long id) {
        User user = entityManager.find(User.class,id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<List<User>> getAll() {
        List<User> allUsers = entityManager.createQuery("FROM user", User.class).getResultList();
        return Optional.ofNullable(allUsers);
    }

    @Override
    public void save(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            entityManager.persist(user);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo User");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            entityManager.remove(user);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo User");
        }
    }

    @Override
    public void delete(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            entityManager.merge(user);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo User");
        }
    }

    @Override
    public Optional<List<User>> getAllInfoByUser(long idUser) {
        return null;
    }

    //Get All User By Home
    public Optional<List<Recipe>> getAllInfoByHome(long idHome) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM recipe d WHERE d.home.id = :idHome", Recipe.class)
                .setParameter("idHome", idHome)
                .getResultList());
    }
}
