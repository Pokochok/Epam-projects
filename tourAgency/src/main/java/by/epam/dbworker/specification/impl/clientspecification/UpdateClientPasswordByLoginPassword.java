package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class UpdateClientPasswordByLoginPassword implements Specification<User> {
    private static final String UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL =
            "UPDATE clients SET password=? WHERE login=? AND password=?;";
    private String newPassword;
    private String login;
    private String password;

    public UpdateClientPasswordByLoginPassword(String newPassword, String login, String Password) {
        this.newPassword = newPassword;
        this.login = login;
        this.password = password;
    }

    @Override
    public String sqlQuery() {
        return UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(3);
        values.push(newPassword);
        values.push(login);
        values.push(password);
        return values;
    }
}