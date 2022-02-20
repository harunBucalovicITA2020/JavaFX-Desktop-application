/*
 * JPA ->> EntityManagerFactory
    Hibernate ->> SessionFactory
 */
package app.business.dao.main;

import app.constants.Constants;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Harun
 */
public interface JpaDao<E> extends Dao<E>{
     
   EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(Constants.PU_NAME);
   
   public EntityManager getEntityManager();
   
     default void executeInsideTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            consumer.accept(entityManager);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }


}

