package by.epam.touragency.repository;

import by.epam.touragency.specification.Specification;

import java.util.Set;

/**
 * Class, which simplifies database interaction
 * @param <T> the type of elements processed by this repository
 */
public interface Repository<T> {

    /**
     * Adds element in database according to specification
     *
     * @param item
     * @param specification which determine adding
     */
    void add(T item, Specification specification);

    /**
     * Updates element in database according to specification
     *
     * @param entity
     * @param specification which determine updating
     */
    void update(T entity, Specification specification);

    /**
     * Removes element in database according to specification
     *
     * @param entity
     * @param specification which determine removing
     */
    void remove(T entity, Specification specification);

    /**
     * Makes a request to database according to specification
     *
     * @param specification which determine request
     * @return entities set
     */
    Set<T> query(Specification specification);

    /**
     * Defines if database has elements according to specification
     *
     * @param specification which determine request
     * @return true if database found element, false - if not
     */
    boolean isExistsQuery(Specification specification);
}
