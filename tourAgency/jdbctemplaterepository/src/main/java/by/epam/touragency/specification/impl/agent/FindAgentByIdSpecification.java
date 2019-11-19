package by.epam.touragency.specification.impl.agent;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAgentByIdSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM users WHERE id=? AND role='AGENT';";
    private long id;

    public FindAgentByIdSpecification(long id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(id);
        return values;
    }
}
