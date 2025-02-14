package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateTourNameByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_TOUR_NAME_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET tour_name=? WHERE id=?;";
    private String tourName;
    private int id;

    public UpdateTourNameByIdSpecification(String tourName, int id) {
        this.tourName = tourName;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_TOUR_NAME_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.add(tourName);
        values.add(id);
        return values;
    }
}
