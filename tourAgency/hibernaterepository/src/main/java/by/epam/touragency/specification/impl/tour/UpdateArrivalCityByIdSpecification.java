package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateArrivalCityByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_ARRIVAL_CITY_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET arrival_city=? WHERE id=?;";
    private String arrivalCity;
    private int id;

    public UpdateArrivalCityByIdSpecification(String arrivalCity, int id) {
        this.arrivalCity = arrivalCity;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_ARRIVAL_CITY_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.add(arrivalCity);
        values.add(id);
        return values;
    }
}