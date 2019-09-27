package by.epam.touragency.specification.impl.order;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdatePaymentStateByIdSpecification implements Specification<Order> {
    private static final String UPDATE_PAYMENT_STATE_SPECIFICATION_SQL_BY_ID =
            "UPDATE orders SET payment_state='true' WHERE id=?;";
    private int id;

    public UpdatePaymentStateByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PAYMENT_STATE_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(id);
        return values;
    }
}
