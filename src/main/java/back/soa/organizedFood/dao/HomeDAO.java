package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Home;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HomeDAO implements DAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    public List getAllByUserId(Long userId) {
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

    @Override
    public List getAllInfoByUser(long idUser) {
        return null;
    }

    public List<Home> getHogaresByUser(Long idUser){
        return entityManager.createQuery(
                        "FROM home d WHERE d.user.id = :idUser", Home.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }
}
