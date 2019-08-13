package by.epam.tourAgency.specification.impl.admin;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindAdminByLoginPasswordSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_LOGIN_PASSWORD = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM admins WHERE login=? AND password=?;";
    private String login;
    private String password;

    public FindAdminByLoginPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_LOGIN_PASSWORD;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(2);
        values.push(login);
        values.push(password);
        return values;
    }
}
