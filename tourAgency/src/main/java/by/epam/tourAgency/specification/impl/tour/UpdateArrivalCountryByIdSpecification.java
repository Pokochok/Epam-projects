package by.epam.tourAgency.specification.impl.tour;

import by.epam.tourAgency.entity.Tour;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateArrivalCountryByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_ARRIVAL_COUNTRY_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET arrival_country=? WHERE id=?;";
    private String arrivalCountry;
    private int id;

    public UpdateArrivalCountryByIdSpecification(String arrivalCountry, int id) {
        this.arrivalCountry = arrivalCountry;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_ARRIVAL_COUNTRY_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(arrivalCountry);
        values.push(id);
        return values;
    }
}
