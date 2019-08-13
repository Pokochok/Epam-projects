package by.epam.tourAgency.specification.impl.admin;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAdminPasswordByLoginPasswordSpecification implements Specification<User> {
    private static final String UPDATE_PASSWORD_BY_LOGIN_PASSWORD_SQL =
            "UPDATE admins SET password=? WHERE login=? AND password=?;";
    private String newPassword;
    private String login;
    private String password;

    public UpdateAdminPasswordByLoginPasswordSpecification(String newPassword, String login, String password) {
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
        values.push(newPassword);
        values.push(login);
        values.push(password);
        return values;
    }
}

