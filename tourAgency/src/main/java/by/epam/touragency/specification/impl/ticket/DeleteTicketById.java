package by.epam.touragency.specification.impl.ticket;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class DeleteTicketById implements Specification<Ticket> {
    private static final String DELETE_SPECIFICATION_SQL_BY_ID = "DELETE FROM tickets WHERE id = ?;";
    private int id;

    public DeleteTicketById(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return DELETE_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(id);
        return values;
    }
}
