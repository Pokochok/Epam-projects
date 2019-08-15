package by.epam.tourAgency.specification;

import java.util.ArrayDeque;

/**
 * For determine the order of execution of the request
 * @param <T> this type of elements will be processed
 */
public interface Specification<T> {
    /**
     * Provides sql query
     * @return sql query
     */
    String sqlQuery();

    /**
     * Provides with parameters
     * @return ArrayDeque with parameter in order, which
     * they located in sql query
     */
    ArrayDeque getParameterQueue();
}
