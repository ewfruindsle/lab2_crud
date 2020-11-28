package dao;

import model.Bill;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Singleton
public class DaoBill extends DaoGeneric<Bill> {

    @PersistenceContext(name = "default")
    public EntityManager entityManager;

    private String str = "info";

    public DaoBill() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Bill> get(int id) {
        return Optional.of(entityManager.find(Bill.class, id));
    }

    @Override
    public List<Bill> getAll() {
        return entityManager.createNamedQuery("Bill.findAll", Bill.class).getResultList();
    }

    @Override
    public void save(Bill bill) {
        entityManager.getTransaction().begin();
        entityManager.persist(bill);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Bill bill) {
        save(bill);
    }

    @Override
    public void delete(Bill bill) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Bill b where b.id = " + bill.getId()).executeUpdate();
        entityManager.getTransaction().commit();
    }
}
