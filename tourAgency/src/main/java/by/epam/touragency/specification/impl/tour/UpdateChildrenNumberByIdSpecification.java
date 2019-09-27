package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateChildrenNumberByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_CHILDREN_NUMBER_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET children_number=? WHERE id=?;";
    private int childrenNumber;
    private int id;

    public UpdateChildrenNumberByIdSpecification(int childrenNumber, int id) {
        this.childrenNumber = childrenNumber;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_CHILDREN_NUMBER_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(childrenNumber);
        values.push(id);
        return values;
    }
}
