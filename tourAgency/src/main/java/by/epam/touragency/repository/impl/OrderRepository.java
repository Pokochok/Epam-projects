package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.OrderRowMapper;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.touragency.specification.impl.client.FindClientByIdSpecification;
import by.epam.touragency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.touragency.specification.impl.tour.FindTourByIdSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<Order> {

    private JdbcTemplate jdbcTemplate;
    private static OrderRepository orderRepository;

    private OrderRepository() {
    }

    @Autowired
    private OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        orderRepository = this;
    }

    public static OrderRepository getInstance() {
        if (orderRepository != null) {
            return orderRepository;
        } else {
            orderRepository = new OrderRepository();
            return orderRepository;
        }
    }

    @Override
    public void add(Order order, Specification specification) throws RepositoryException {
        jdbcTemplate.update(specification.sqlQuery(), order.getTour().getId(), order.getTicket().getId(),
                order.getClient().getId(), order.getAgent().getId());
    }

    @Override
    public void update(Order entity, Specification specification) throws RepositoryException {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public void remove(Order entity, Specification specification) throws RepositoryException {
        jdbcTemplate.update(specification.sqlQuery(), specification.getParameterQueue().toArray());
    }

    @Override
    public Set<Order> query(Specification specification) throws RepositoryException {
        Set<Order> orders = new HashSet<>(jdbcTemplate.query(specification.sqlQuery(),
                specification.getParameterQueue().toArray(), new OrderRowMapper()));
        for (Order order : orders) {
            Specification tourQuery = new FindTourByIdSpecification(order.getTourId());
            Specification ticketQuery = new FindTicketByIdSpecification(order.getTicketId());
            Specification userQuery = new FindClientByIdSpecification(order.getClientId());
            Specification agentQuery = new FindAgentByIdSpecification(order.getAgentId());
            order.setId(order.getId());
            order.setPaymentState(order.getPaymentState());
            order.setTour(TourRepository.getInstance().query(tourQuery).iterator().next());
            order.setTicket(TicketRepository.getInstance().query(ticketQuery).iterator().next());
            order.setClient(UserRepository.getInstance().query(userQuery).iterator().next());
            order.setAgent(UserRepository.getInstance().query(agentQuery).iterator().next());
        }
        return orders;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}