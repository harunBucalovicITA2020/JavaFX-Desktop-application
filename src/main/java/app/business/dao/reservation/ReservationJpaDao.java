package app.business.dao.reservation;

import app.business.dao.main.JpaDao;
import app.model.Reservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Harun
 */
public class ReservationJpaDao implements JpaDao<Reservation>{
    
    EntityManager reservationEntityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager getEntityManager() {
       return reservationEntityManager;
    }

    @Override
    public void save(Reservation entity) {
        executeInsideTransaction(consumer->reservationEntityManager.persist(entity));
    }

    @Override
    public void delete(Reservation entiy) {
        executeInsideTransaction(consumer->reservationEntityManager.remove(entiy));
    }

    @Override
    public void update(Reservation entity) {
        executeInsideTransaction(consumer-> reservationEntityManager.merge(entity));
    }

    @Override
    public Reservation get(long id) {
        return reservationEntityManager.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> getAll() {
        Query query = reservationEntityManager.createNamedQuery("Reservation.findAll", Reservation.class);
        return query.getResultList();
    }
    
}
