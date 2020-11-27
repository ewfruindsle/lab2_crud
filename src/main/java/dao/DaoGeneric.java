package dao;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public abstract class DaoGeneric<T> {

    protected EntityManager entityManager;

    abstract public Optional<T> get(int id);

    abstract public List<T> getAll();

    abstract public void save(T t);

    abstract public void update(T t);

    abstract public void delete(T t);
}

