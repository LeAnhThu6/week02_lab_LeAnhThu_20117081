package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CustomerRepository() {
        em = Persistence
                .createEntityManagerFactory("lab02")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insertEmp(Customer customer) {
//        em.persist(customer);
        try {
            trans.begin();
            em.persist(customer);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }



    public void update(Customer customer) {
        try {
            trans.begin();
            em.merge(customer);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public Optional<Customer> findbyId(long id) {
        TypedQuery<Customer> query = em.createQuery("select e from Customer e where e.id=:id", Customer.class);
        query.setParameter("id", id);
        Customer cus = query.getSingleResult();
        return cus == null ? Optional.empty() : Optional.of(cus);
    }

    public List<Customer> getAllEmp() {
        //paging if possible
        return em.createNamedQuery("Customer.findAll", Customer.class)
                .getResultList();
    }
}
