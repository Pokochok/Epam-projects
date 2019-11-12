package by.epam.touragency.specification.impl.user;

import by.epam.touragency.entity.User;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindUserByEmailSpecification implements Specification<User> {
        private static final String FIND_SPECIFICATION_SQL_BY_EMAIL = "FROM User WHERE email=?1";
        private String email;

    public FindUserByEmailSpecification(String email) {
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
