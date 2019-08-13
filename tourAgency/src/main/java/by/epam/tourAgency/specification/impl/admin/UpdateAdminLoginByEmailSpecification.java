package by.epam.tourAgency.specification.impl.admin;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class UpdateAdminLoginByEmailSpecification implements Specification<User> {
    private static final String UPDATE_LOGIN_SPECIFICATION_SQL_BY_EMAIL =
            "UPDATE admins SET login=? WHERE email=?;";
    private String login;
    private String email;

    public UpdateAdminLoginByEmailSpecification(String login, String email) {
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
        values.push(login);
        values.push(email);
        return values;    }
}
