package dao;

import model.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class DaoService extends DaoGeneric<Service> {

    public DaoService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Service> get(int id) {
        return Optional.of(entityManager.find(Service.class, id));
    }

    @Override
    public List<Service> getAll() {
        return entityManager.createNamedQuery("Service.findAll", Service.class).getResultList();
    }

    @Override
    public void save(Service service) {
        entityManager.getTransaction().begin();
        entityManager.persist(service);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Service service) {
        save(service);
    }

    @Override
    public void delete(Service service) {
        entityManager.getTransaction().begin();
        entityManager.remove(service);
        entityManager.getTransaction().commit();
    }
}
