package by.epam.touragency.specification.impl.ticket;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindTicketsByAllContentSpecification implements Specification<Ticket> {
    private static final String FIND_SPECIFICATION_SQL_BY_CONTENT = "FROM Ticket t WHERE t.flightNumber=?1 AND " +
            "t.ticketNumber=?2 AND t.departureCity=?3 AND t.arrivalCity=?4 AND t.departureDateTime=?5 AND t.arrivalDateTime=?6";
    private int flightNumber;
    private int ticketNumber;
    private String departureCity;
    private String arrivalCity;
    private long departureDateTime;
    private long arrivalDateTime;

    public FindTicketsByAllContentSpecification(int flightNumber, int ticketNumber, String departureCity, String arrivalCity,
                                                long departureDateTime, long arrivalDateTime) {
        this.flightNumber = flightNumber;
        this.ticketNumber = ticketNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_CONTENT;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(flightNumber);
        values.add(ticketNumber);
        values.add(departureCity);
        values.add(arrivalCity);
        values.add(departureDateTime);
        values.add(arrivalDateTime);
        return values;
    }
}

