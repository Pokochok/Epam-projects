package by.epam.dbworker.specification.impl.agentspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindAgentByPhoneNumberSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM agents WHERE phone_number=?;";
    private String phoneNumber;
    private ArrayDeque values;

    public FindAgentByPhoneNumberSpecification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        values = new ArrayDeque(1);
        values.push(phoneNumber);
    }

    @Override
    public boolean specify(User entity) {
        return phoneNumber.equals(entity.getPhoneNumber());
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_PHONE_NUMBER;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
