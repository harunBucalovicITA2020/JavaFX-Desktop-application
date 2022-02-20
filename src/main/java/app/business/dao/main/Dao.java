/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.business.dao.main;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Harun
 * @param <E>
 */
public interface Dao<E> {

    void save(E entity);

    void delete(E entiy);

    void update(E entity);

   // Optional<E> get(long id);

    E get(long id);

    List<E> getAll();

}
