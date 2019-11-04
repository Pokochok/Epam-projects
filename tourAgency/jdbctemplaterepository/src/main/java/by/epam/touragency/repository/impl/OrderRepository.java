package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.*;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.touragency.specification.impl.client.FindClientByIdSpecification;
import by.epam.touragency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.touragency.specification.impl.tour.FindTourByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<Order> {
    private static final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;

    private Repository<Tour> tourRepository;

    private Repository<User> userRepository;

    private Repository<Ticket> ticketRepository;

    public OrderRepository(){
    }

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate, @Qualifier("tourRepository") Repository tourRepository,
                           @Qualifier("userRepository") Repository userRepository,
                           @Qualifier("ticketRepository") Repository ticketRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void add(Order order, Specification specification){
        jdbcTemplate.update(specification.sqlQuery(), order.getPaymentState(), order.getTour().getId(), order.getTicket().getId(),
                order.getClient().getId(), order.getAgent().getId());
    }

    @Override
    public void update(Order entity, Specification specification) {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public void remove(Order entity, Specification specification){
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public Set<Order> query(Specification specification){
        Set<Order> orders = new HashSet<>(jdbcTemplate.query(specification.sqlQuery(),
                specification.getParameterQueue().toArray(), new OrderRowMapper()));
        for (Order order : orders) {
            Specification tourQuery = new FindTourByIdSpecification(order.getTourId());
            Specification ticketQuery = new FindTicketByIdSpecification(order.getTicketId());
            Specification userQuery = new FindClientByIdSpecification(order.getClientId());
            Specification agentQuery = new FindAgentByIdSpecification(order.getAgentId());
            order.setId(order.getId());
            order.setPaymentState(order.getPaymentState());
            order.setTour(tourRepository.query(tourQuery).iterator().next());
            order.setTicket(ticketRepository.query(ticketQuery).iterator().next());
            order.setClient(userRepository.query(userQuery).iterator().next());
            order.setAgent(userRepository.query(agentQuery).iterator().next());
        }
        return orders;
    }

    @Override
    public boolean isExistsQuery(Specification specification){
        try {
            return !new HashSet<>(jdbcTemplate.query(specification.sqlQuery(),
                    specification.getParameterQueue().toArray(), new OrderRowMapper())).isEmpty();
        }catch (RuntimeException e){
            LOGGER.error("Error while getting is exists query ");
            throw new RuntimeException("Error while getting is exists query ", e);
        }
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}