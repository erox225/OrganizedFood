package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    public List<Storage> getAll() {
        List<Storage> todasLasStorageEntities = new ArrayList<>();
        todasLasStorageEntities = entityManager.createQuery("FROM storage", Storage.class).getResultList();
        return todasLasStorageEntities;
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
    public void update(Object o, String[] params) {

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

    public List<Storage> getAllInfoByUser(String idUser) {
        System.out.println("getAllInfoByUser en StorageDAO with idUser = "+idUser);

        List<Storage> todasLasStorageEntities = new ArrayList<>();
        todasLasStorageEntities = entityManager.createQuery("FROM Storage", Storage.class).getResultList();

        List<Storage> result = entityManager.createQuery(
                        "SELECT d FROM Storage d " +
                                "JOIN d.home h " +
                                "JOIN h.users u " +
                                "WHERE u.id = :idUser", Storage.class)
                .setParameter("idUser", Long.parseLong(idUser))
                .getResultList();
        return result;
    }
}
