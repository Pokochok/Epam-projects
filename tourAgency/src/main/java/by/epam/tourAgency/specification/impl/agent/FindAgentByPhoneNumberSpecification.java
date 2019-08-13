package by.epam.tourAgency.specification.impl.agent;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindAgentByPhoneNumberSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM agents WHERE phone_number=?;";
    private String phoneNumber;

    public FindAgentByPhoneNumberSpecification(String phoneNumber) {
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
