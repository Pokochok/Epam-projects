package by.epam.touragency.specification.impl.agent;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAgentByLoginSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_LOGIN = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM agents WHERE login=?;";
    private String login;

    public FindAgentByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_LOGIN;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(login);
        return values;
    }
}
