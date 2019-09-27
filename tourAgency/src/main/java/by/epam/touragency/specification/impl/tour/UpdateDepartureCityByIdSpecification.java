package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateDepartureCityByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_DEPARTURE_CITY_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET departure_city=? WHERE id=?;";
    private String departureCity;
    private int id;

    public UpdateDepartureCityByIdSpecification(String departureCity, int id) {
        this.departureCity = departureCity;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_DEPARTURE_CITY_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(departureCity);
        values.push(id);
        return values;
    }
}
