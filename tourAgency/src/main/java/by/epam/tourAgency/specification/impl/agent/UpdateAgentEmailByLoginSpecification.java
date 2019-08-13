package by.epam.tourAgency.specification.impl.agent;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAgentEmailByLoginSpecification implements Specification<User> {
    private static final String UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE agents SET email=? WHERE login=?;";
    private String email;
    private String login;

    public UpdateAgentEmailByLoginSpecification(String email, String login) {
        this.email = email;
        this.login = login;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(email);
        values.push(login);
        return values;
    }
}
