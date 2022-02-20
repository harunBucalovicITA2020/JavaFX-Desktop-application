/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.business.dao.privilege;

import app.business.dao.main.JpaDao;
import app.model.Privilege;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Harun
 */
public class PrivilegeJPAdao implements JpaDao<Privilege> {

    EntityManager privilegeEntityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager getEntityManager() {
        return privilegeEntityManager;
    }

    @Override
    public void save(Privilege entity) {
        executeInsideTransaction(consumer -> privilegeEntityManager.persist(entity));

    }

    @Override
    public void delete(Privilege entiy) {
        executeInsideTransaction(consumer->privilegeEntityManager.remove(entiy));
    }

    @Override
    public void update(Privilege entity) {
        executeInsideTransaction(consumer-> privilegeEntityManager.merge(entity));
    }

    @Override
    public Privilege get(long id) {
       return privilegeEntityManager.find(Privilege.class, id);
    }

    @Override
    public List<Privilege> getAll() {
     Query query = privilegeEntityManager.createNamedQuery("Privilege.findAll");
     return query.getResultList();
    }

}
