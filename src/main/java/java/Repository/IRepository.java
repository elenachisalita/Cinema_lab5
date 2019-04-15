package java.Repository;

import java.Domain.Entity;
import java.util.List;

public interface IRepository<T extends Entity> {


    /**
     * returns a entity by
     * @param id of the entity
     * @return a entity
     */
    T getById(String id);

    /**
     * adds a movie
     * @param entity to add
     */
    void insert(T entity);

    /**
     * updates a movie
     * @param entity
     */
    void update(T entity);

    /**
     * removes a movie
     * @param id of the movie we want to remove
     */
    void remove(String id);

    /**
     *
     * @return a list with all entities
     */
    List<T> getAll();


}
