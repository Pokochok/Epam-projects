package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.math.BigDecimal;
import java.util.ArrayDeque;

public class UpdatePriceByIdSpecification implements Specification<Tour> {
    private static final String UPDATE_PRICE_SPECIFICATION_SQL_BY_ID =
            "UPDATE tours SET price=? WHERE id=?;";
    private BigDecimal price;
    private int id;

    public UpdatePriceByIdSpecification(BigDecimal price, int id) {
        this.price = price;
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PRICE_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(price);
        values.push(id);
        return values;
    }
}
