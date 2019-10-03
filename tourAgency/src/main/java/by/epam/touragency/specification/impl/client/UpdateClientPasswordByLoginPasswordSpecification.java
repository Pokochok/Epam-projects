package by.epam.touragency.specification.impl.client;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientPasswordByLoginPasswordSpecification implements Specification<User> {
    private static final String UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL =
            "UPDATE clients SET password=? WHERE login=? AND password=?;";
    private String newPassword;
    private String login;
    private String password;

    public UpdateClientPasswordByLoginPasswordSpecification(String newPassword, String login, String password) {
        this.newPassword = newPassword;
        this.login = login;
        this.password = password;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(3);
        values.push(password);
        values.push(login);
        values.push(newPassword);
        return values;
    }
}