package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateArrivalDateByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_ARRIVAL_DATE_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET arrival_date=? WHERE id=?;";
    private long arrivalDate;
    private int id;

    public UpdateArrivalDateByIdSpecification(long arrivalDate, int id) {
        this.arrivalDate = arrivalDate;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_ARRIVAL_DATE_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(arrivalDate);
        values.push(id);
        return values;
    }
}
