package by.epam.tourAgency.specification;

import java.util.ArrayDeque;

public interface Specification<T> {
    String sqlQuery();
    ArrayDeque getParameterQueue();
}
