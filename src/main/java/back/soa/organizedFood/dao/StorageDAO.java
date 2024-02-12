package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Product;
import back.soa.organizedFood.entity.Storage;
import back.soa.organizedFood.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StorageDAO implements DAO {

    EntityManagerFactory enf = Persistence.createEntityManagerFactory("Organizedfood");
    EntityManager entityManager = enf.createEntityManager();


    @Override
    public Optional<Storage> get(long id) {
        Storage storage = entityManager.find(Storage.class,id);
        return Optional.ofNullable(storage);
    }

    @Override
    public Optional<List<Storage>> getAll() {
        List<Storage> todasLasStorageEntities = entityManager.createQuery("FROM storage", Storage.class).getResultList();
        return Optional.ofNullable(todasLasStorageEntities);
    }

    @Override
    public void save(Object o) {
        if (o instanceof Storage) {
            Storage storage = (Storage) o;
            entityManager.persist(storage);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Despensa");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Storage) {
            Storage storage = (Storage) o;
            entityManager.merge(storage);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Despensa");
        }
    }

    @Override
    public void delete(Object o) {
        if (o instanceof Storage) {
            Storage storage = (Storage) o;
            entityManager.remove(storage);
        } else {
            throw new IllegalArgumentException("El objeto no es de tipo Despensa");
        }
    }

    @Override
    public Optional<List<Storage>> getAllInfoByUser(long idUser) {

        TypedQuery<Storage> query = entityManager.createQuery(
                        "SELECT d FROM Storage d " +
                                "JOIN d.home h " +
                                "JOIN h.users u " +
                                "WHERE u.id = :idUser", Storage.class)
                .setParameter("idUser", idUser);

        try {
            List<Storage> result = query.getResultList();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
