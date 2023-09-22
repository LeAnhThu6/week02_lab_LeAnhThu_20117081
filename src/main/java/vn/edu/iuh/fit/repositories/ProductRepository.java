package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.Product;

public class ProductRepository {
    //    @PersistenceContext
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_02")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insert(Product product) {
//        em.persist(product);
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
        }catch (Exception e){
            trans.rollback();
            logger.error(e.getMessage());
        }
    }
}
