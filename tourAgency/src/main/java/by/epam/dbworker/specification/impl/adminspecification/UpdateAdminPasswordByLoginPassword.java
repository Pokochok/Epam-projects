package by.epam.dbworker.specification.impl.adminspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAdminPasswordByLoginPassword implements Specification<User> {
    private static final String UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL =
            "UPDATE admins SET password=? WHERE login=? AND password=?;";
    private String newPassword;
    private String login;
    private String password;

    public UpdateAdminPasswordByLoginPassword(String newPassword, String login, String password) {
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

