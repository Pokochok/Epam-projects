package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateUserPasswordByLoginSpecification implements Specification<User> {
    private static final String UPDATE_PASSWORD_BY_LOGIN_SQL =
            "UPDATE users SET password=? WHERE login=?;";
    private String newPassword;
    private String login;

    public UpdateUserPasswordByLoginSpecification(String login, String newPassword) {
        this.newPassword = newPassword;
        this.login = login;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PASSWORD_BY_LOGIN_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(login);
        values.push(newPassword);
        return values;
    }
}
