package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Home;
import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.entity.Storage;
import back.soa.organizedFood.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HomeDAO implements DAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional get(long id) {
        Home home = entityManager.find(Home.class,id);
        return Optional.ofNullable(home);
    }

    @Override
    public Optional<List<Home>> getAll() {
        List<Home> allHomes = entityManager.createQuery("FROM home", Home.class).getResultList();
        return Optional.ofNullable(allHomes);
    }


    @Override
    public void save(Object o) {
        if (o instanceof Home) {
            Home home = (Home) o;
            entityManager.persist(home);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Home");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Home) {
            Home home = (Home) o;
            entityManager.merge(home);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Home");
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
    public Optional<List> getAllInfoByUser(long idUser) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM home d WHERE d.user.id = :idUser", Home.class)
                .setParameter("idUser", idUser)
                .getResultList());
    }

    public Optional<List> getAllInfo(Long userId) {
    }
}
