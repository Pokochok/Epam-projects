package by.epam.dbworker.specification;

import java.util.ArrayDeque;

public interface Specification<T> extends SqlSpecification {
    default boolean specify(T entity){
        return false;
    }
    ArrayDeque getParameterQueue();
}
