package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientSurnameByLoginSpecification implements Specification<User> {
    private static final String UPDATE_EMAIL_SPECIFICATION_SQL_BY_LOGIN =
            "UPDATE admins SET surname=? WHERE login=?;";
    private String surname;
    private String login;

    public UpdateClientSurnameByLoginSpecification(String surname, String login) {
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
        values.push(login);
        values.push(surname);
        return values;    }
}
