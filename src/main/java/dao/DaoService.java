package dao;

import model.Service;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(value = 3, unit = TimeUnit.SECONDS)
public class DaoService extends DaoGeneric<Service> {

    private List<Service> cacheService;
    @PersistenceContext(name = "default")
    public EntityManager entityManager;

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
        if(cacheService != null){
            return cacheService;
        }
        cacheService = entityManager.createNamedQuery("Service.findAll", Service.class).getResultList();
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

    @Remove
    void remove(){
        cacheService = null;
    }
}
