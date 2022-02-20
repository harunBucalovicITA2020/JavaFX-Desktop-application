package app.business.dao.guest;

import app.business.dao.main.JpaDao;
import app.model.Guest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Harun
 */
public class GuestJpaDao implements JpaDao<Guest>{
    
    EntityManager guestEntityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager getEntityManager() {
       return guestEntityManager;
    }

    @Override
    public void save(Guest entity) {
        executeInsideTransaction(consumer->guestEntityManager.persist(entity));
    }

    @Override
    public void delete(Guest entiy) {
        executeInsideTransaction(consumer-> guestEntityManager.remove(entiy));
    }

    @Override
    public void update(Guest entity) {
        executeInsideTransaction(consumer->guestEntityManager.merge(entity));
    }

    @Override
    public Guest get(long id) {
             return guestEntityManager.find(Guest.class, id);
    }

    @Override
    public List<Guest> getAll() {
        Query query = guestEntityManager.createNamedQuery("Guest.findAll");
        return query.getResultList();
    }
    
    
}
