package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAdultsNumberByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_ADULTS_NUMBER_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET adults_number=? WHERE id=?;";
    private int adultsNumber;
    private int id;

    public UpdateAdultsNumberByIdSpecification(int adultsNumber, int id) {
        this.adultsNumber = adultsNumber;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_ADULTS_NUMBER_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(adultsNumber);
        values.push(id);
        return values;
    }
}
