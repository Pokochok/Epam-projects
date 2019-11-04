package by.epam.touragency.specification.impl.admin;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAdminByPhoneNumberSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM users WHERE phone_number=?;";
    private String phoneNumber;

    public FindAdminByPhoneNumberSpecification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(phoneNumber);
        return values;
    }
}

