package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindTourByNameSpecification implements Specification<Tour> {
    private static final String FIND_TOUR_NAME_SPECIFICATION_SQL_BY_ID = "FROM Tour WHERE tourName=?1";
    private String tourName;

    public FindTourByNameSpecification(String tourName) {
        this.tourName = tourName;
    }

    @Override
    public String sqlQuery() {
        return FIND_TOUR_NAME_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(tourName);
        return values;
    }
}
