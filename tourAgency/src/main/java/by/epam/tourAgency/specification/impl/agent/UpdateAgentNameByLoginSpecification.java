package by.epam.tourAgency.specification.impl.agent;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAgentNameByLoginSpecification implements Specification<User> {
    private static final String UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE agents SET name=? WHERE login=?;";
    private String name;
    private String login;

    public UpdateAgentNameByLoginSpecification(String name, String login) {
        this.name = name;
        this.login = login;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(name);
        values.push(login);
        return values;    }
}
