package by.epam.tourAgency.specification.impl.admin;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindAdminByEmailSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_EMAIL = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM admins WHERE email=?;";
    private String email;

    public FindAdminByEmailSpecification(String email) {
        this.email = email;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_EMAIL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>();
        values.push(email);
        return values;
    }
}
