package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.*;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.touragency.specification.impl.client.FindClientByIdSpecification;
import by.epam.touragency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.touragency.specification.impl.tour.FindTourByIdSpecification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository("orderRepository")
public class HibernateOrderRepository  implements Repository<Order> {
    private Session session;

    private Repository<Tour> tourRepository;

    private Repository<User> userRepository;

    private Repository<Ticket> ticketRepository;

    public HibernateOrderRepository(){
    }

    @Autowired
    public HibernateOrderRepository(Session session, @Qualifier("tourRepository") Repository<Tour> tourRepository,
                                    @Qualifier("userRepository") Repository<User> userRepository,
                                    @Qualifier("ticketRepository") Repository<Ticket> ticketRepository) {
        this.session = session;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void add(Order order, Specification specification){
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    @Override
    public void update(Order entity, Specification specification) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void remove(Order entity, Specification specification){
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public Set<Order> query(Specification specification){
        Set<Order> orders = new HashSet<>(session.createQuery(specification.sqlQuery(), Order.class).list());
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
        return !query(specification).isEmpty();
    }
}
