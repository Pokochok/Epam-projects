package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateDepartureDateByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_DEPARTURE_DATE_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET departure_date=? WHERE id=?;";
    private long departureDate;
    private int id;

    public UpdateDepartureDateByIdSpecification(long departureDate, int id) {
        this.departureDate = departureDate;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_DEPARTURE_DATE_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(departureDate);
        values.push(id);
        return values;
    }
}
