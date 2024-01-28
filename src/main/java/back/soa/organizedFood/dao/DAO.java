package back.soa.organizedFood.dao;

import back.soa.organizedFood.entity.Storage;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(long id);

    Optional<List<T>> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    Optional<List<T>> getAllInfoByUser(long idUser);
}
