package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindTourByIdSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "FROM Tour WHERE id=?1";
    private int id;

    public FindTourByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(id);
        return values;
    }
}