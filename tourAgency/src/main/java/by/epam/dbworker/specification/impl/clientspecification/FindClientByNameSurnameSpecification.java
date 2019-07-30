package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByNameSurnameSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_NAME_SURNAME = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE name=? AND surname=?;";
    private String name;
    private String surname;
    private ArrayDeque values;

    public FindClientByNameSurnameSpecification(String name, String surname) {
        this.name = name;
        this.surname = surname;
        values = new ArrayDeque(2);
        values.push(name);
        values.push(surname);
    }

    @Override
    public boolean specify(User entity) {
        return surname.equals(entity.getSurname()) && name.equals(entity.getName());
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_NAME_SURNAME;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}

