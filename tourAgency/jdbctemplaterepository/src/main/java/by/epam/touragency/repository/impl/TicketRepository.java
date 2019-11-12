package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.entity.TicketRowMapper;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.ticket.AddTicketSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository
public class TicketRepository implements Repository<Ticket> {

    private JdbcTemplate jdbcTemplate;
    private static TicketRepository ticketRepository;

    private TicketRepository() {
    }

    @Autowired
    public TicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        ticketRepository = this;
    }

    public static TicketRepository getInstance() {
        if (ticketRepository != null) {
            return ticketRepository;
        } else {
            ticketRepository = new TicketRepository();
            return ticketRepository;
        }
    }

    @Override
    public void add(Ticket ticket, Specification specification){
        Specification addSpecification = new AddTicketSpecification(ticket);
        jdbcTemplate.update(addSpecification.sqlQuery(), ticket.getFlightNumber(), ticket.getTicketNumber(),
                ticket.getDepartureCity(), ticket.getArrivalCity(), ticket.getDepartureDateTimeLong(),
                ticket.getArrivalDateTimeLong());
    }

    @Override
    public void update(Ticket entity, Specification specification) {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public void remove(Ticket entity, Specification specification) {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public Set<Ticket> query(Specification specification) {
        return new HashSet<>(jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                new TicketRowMapper()));
    }

    @Override
    public boolean isExistsQuery(Specification specification) {
        try {
            return !new HashSet<>(jdbcTemplate.query(specification.sqlQuery(), specification.getParameterQueue().toArray(),
                    new TicketRowMapper())).isEmpty();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while getting is exists query ", e);
        }
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
