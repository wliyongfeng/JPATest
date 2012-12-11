package li.test.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class CustomerDao {
    private final EntityManagerFactory factory;
    private final EntityManager manager;

    public CustomerDao() {
        factory = Persistence.createEntityManagerFactory("production");
        manager = factory.createEntityManager();
    }

    public void insert(Customer customer) {
        EntityTransaction t = manager.getTransaction();
        t.begin();
        manager.persist(customer);
        // manager.merge(customer);
        try {
            t.commit();
        } catch (RollbackException e) {
            t.rollback();
        }

        // manager.flush();
    }

    public Customer find(int id) {
        Customer customer = manager.find(Customer.class, id);
        if (null != customer) {
            return customer;
        }

        return null;
    }

    public List<Customer> findAll() {
        // EntityTransaction t = manager.getTransaction();
        // t.begin();
        // t.commit();
        TypedQuery<Customer> customers = manager.createQuery(
                "select cus from Customer cus", Customer.class);
        return customers.getResultList();
    }
}
