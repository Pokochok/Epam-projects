package by.epam.tourAgency.specification.impl.ticket;

import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindAllTicketsSpecification implements Specification<Ticket> {
    private static final String FIND_ALL_SPECIFICATION_SQL = "SELECT id, flight_number, ticket_number, " +
            "departure_city, arrival_city, departure_datetime, arrival_datetime FROM tickets WHERE id!=0;";

    public FindAllTicketsSpecification() {

    }

    @Override
    public String sqlQuery() {
        return FIND_ALL_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        return new ArrayDeque<>(0);
    }
}

