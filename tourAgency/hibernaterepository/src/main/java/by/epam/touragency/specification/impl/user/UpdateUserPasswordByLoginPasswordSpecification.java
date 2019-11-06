package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateUserPasswordByLoginPasswordSpecification implements Specification<User> {
    private static final String UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL =
            "UPDATE User SET password=?1 WHERE login=?2 AND password=?3;";
    private String newPassword;
    private String login;
    private String password;

    public UpdateUserPasswordByLoginPasswordSpecification(String newPassword, String login, String password) {
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

