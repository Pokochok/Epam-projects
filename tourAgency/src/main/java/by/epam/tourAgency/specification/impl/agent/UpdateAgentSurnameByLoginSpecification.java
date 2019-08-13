package by.epam.tourAgency.specification.impl.agent;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAgentSurnameByLoginSpecification implements Specification<User> {
    private static final String UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE agents SET surname=? WHERE login=?;";
    private String surname;
    private String login;

    public UpdateAgentSurnameByLoginSpecification(String surname, String login) {
        this.surname = surname;
        this.login = login;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(surname);
        values.push(login);
        return values;    }
}
