package by.epam.dbworker.specification.impl.clientspecification;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindClientByIdSpecification implements Specification<User> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, name, surname, email, phone_number, login, " +
            "password, role, status FROM clients WHERE id=?;";
    private int id;
    private ArrayDeque values;

    public FindClientByIdSpecification(int id) {
        this.id = id;
        values = new ArrayDeque(1);
        values.push(id);
    }

    @Override
    public boolean specify(User entity) {
        return entity.getId() == id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}
