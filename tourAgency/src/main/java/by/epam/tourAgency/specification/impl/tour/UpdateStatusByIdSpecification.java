package by.epam.tourAgency.specification.impl.tour;

import by.epam.tourAgency.entity.Tour;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateStatusByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_STATUS_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET status=? WHERE id=?;";
    private String status;
    private int id;

    public UpdateStatusByIdSpecification(String status, int id) {
        this.status = status;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_STATUS_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(status);
        values.push(id);
        return values;
    }
}
