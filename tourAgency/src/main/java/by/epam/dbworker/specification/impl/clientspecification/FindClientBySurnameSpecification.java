package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindClientBySurnameSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_SURNAME = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE surname=?;";
    private String surname;
    private ArrayDeque values;

    public FindClientBySurnameSpecification(String surname) {
        this.surname = surname;
        values = new ArrayDeque(1);
        values.push(surname);
    }

    @Override
    public boolean specify(User entity) {
        return surname.equals(entity.getSurname());
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_SURNAME;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
