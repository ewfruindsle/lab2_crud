package dao;

import model.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class DaoClient extends DaoGeneric<Client> {

    @PersistenceContext(name = "default")
    public EntityManager entityManager;

    public DaoClient() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Client> get(int id) {
        return Optional.of(entityManager.find(Client.class, id));
    }

    @Override
    public List<Client> getAll() {
        return entityManager.createNamedQuery("Client.findAll", Client.class).getResultList();
    }

    @Override
    public void save(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Client client) {
        save(client);
        updateDbData();
    }

    @Override
    public void delete(Client client) {
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
    }

    void updateDbData(){
        try{
            Thread.sleep(1000);
            System.out.println("Data was updated");
        } catch (Exception ex){
            System.out.println("Data wasn't updated");
        }
    }
}
