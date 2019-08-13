package by.epam.tourAgency.specification.impl.client;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByPhoneNumberSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE phone_number=?;";
    private String phoneNumber;

    public FindClientByPhoneNumberSpecification(String phoneNumber) {
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

