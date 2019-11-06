package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindUserByPhoneNumberSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER = "FROM users WHERE phone_number=?1;";
    private String phoneNumber;

    public FindUserByPhoneNumberSpecification(String phoneNumber) {
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
