package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateUserLoginByEmailSpecification implements Specification<User> {
    private static final String UPDATE_LOGIN_SPECIFICATION_SQL_BY_EMAIL =
            "UPDATE User SET login=?1 WHERE email=?2;";
    private String login;
    private String email;

    public UpdateUserLoginByEmailSpecification(String login, String email) {
        this.login = login;
        this.email = email;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_LOGIN_SPECIFICATION_SQL_BY_EMAIL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(email);
        values.push(login);
        return values;    }
}
