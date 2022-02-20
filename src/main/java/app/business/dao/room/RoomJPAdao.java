package app.business.dao.room;

import app.business.dao.main.JpaDao;
import app.constants.MessageDialog;
import app.model.Room;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Harun
 */
public class RoomJPAdao implements JpaDao<Room> {

    EntityManager roomEntityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager getEntityManager() {
        return roomEntityManager;
    }

    @Override
    public void save(Room entity) {
        executeInsideTransaction(em -> roomEntityManager.persist(entity));
    }

    @Override
    public void delete(Room entiy) {
        executeInsideTransaction(em -> roomEntityManager.remove(entiy));
    }

    @Override
    public void update(Room entity) {
        executeInsideTransaction(em -> roomEntityManager.merge(entity));
    }

    @Override
    public Room get(long id) {
        return roomEntityManager.find(Room.class, id);
    }

    @Override
    public List<Room> getAll() {
        Query query = roomEntityManager.createNamedQuery("Room.findAll");
        return query.getResultList();
    }

   
}
