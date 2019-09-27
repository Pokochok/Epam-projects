package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateNutritionByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_NUTRITION_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET nutrition=? WHERE id=?;";
    private String nutrition;
    private int id;

    public UpdateNutritionByIdSpecification(String nutrition, int id) {
        this.nutrition = nutrition;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_NUTRITION_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(nutrition);
        values.push(id);
        return values;
    }
}
