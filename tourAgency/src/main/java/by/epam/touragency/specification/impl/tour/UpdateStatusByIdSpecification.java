package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

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
        values.add(status);
        values.add(id);
        return values;
    }
}
