package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductDAO implements DAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional get(long id) {
        Product product = entityManager.find(Product.class,id);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<List<Product>> getAll() {
        List<Product> allProducts = entityManager.createQuery("FROM product", Product.class).getResultList();
        return Optional.ofNullable(allProducts);
    }

    @Override
    public void save(Object o) {
        if (o instanceof Product) {
            Product product = (Product) o;
            entityManager.persist(product);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Home");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Product) {
            Product product = (Product) o;
            entityManager.merge(product);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Product");
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
    public Optional<List<Product>> getAllInfoByUser(long idUser) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM product d WHERE d.user.id = :idUser", Product.class)
                .setParameter("idUser", idUser)
                .getResultList());
    }

    //Get Products by Home
    public Optional<List<Product>> getAllInfoByHome(long idHome) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM product d WHERE d.home.id = :idHome", Product.class)
                .setParameter("idHome", idHome)
                .getResultList());
    }

    //Get Products by Recipe
    public Optional<List<Product>> getAllInfoByRecipe(long idRecipe) {
        return Optional.ofNullable(entityManager.createQuery(
                        "FROM product d WHERE d.recipe.id = :idRecipe", Product.class)
                .setParameter("idRecipe", idRecipe)
                .getResultList());
    }
}
