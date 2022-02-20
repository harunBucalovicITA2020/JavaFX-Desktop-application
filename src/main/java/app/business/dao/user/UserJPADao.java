/*

    @Override
    public Optional<User> get(long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }
 private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("com.solutions.hotel_HotelMvnApplication_jar_1.0PU");
 
 EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e.getMessage());
        }

 * DAO PATTERN SA JPA!!!
 */
package app.business.dao.user;

import app.business.dao.main.JpaDao;
import app.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Harun
 */
public class UserJPADao implements JpaDao<User> {

    private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public void save(User entity) {
        executeInsideTransaction(em -> getEntityManager().persist(entity));
    }

    @Override
    public void delete(User entiy) {
        executeInsideTransaction(em -> entityManager.remove(em.merge(entiy)));
    }

    @Override
    public void update(User entity) {
        executeInsideTransaction(em -> entityManager.merge(entity));
    }

    @Override
    public User get(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public User loginUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("User.findByUsernamePassword");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            System.err.println("Not exist user = " + username);
            return null;
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e.getMessage());

        }

    }

    public User loginUserByUsernameOnly(String username) {

        if (username == null || username.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            System.err.format("Not existing user with username '%s'%n", username);
            return null;
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
