package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Repository
public class HibernateTicketRepository implements Repository<Ticket> {

    private Session session;

    @Autowired
    public HibernateTicketRepository(SessionFactory sessionFactory) {
        session = sessionFactory.openSession();
    }

    @Override
    public void add(Ticket ticket, Specification specification){
        session.beginTransaction();
        session.save(ticket);
        session.getTransaction().commit();
    }

    @Override
    public void update(Ticket entity, Specification specification) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void remove(Ticket entity, Specification specification) {
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public Set<Ticket> query(Specification specification) {
        Query<Ticket> query = session.createQuery(specification.sqlQuery(), Ticket.class);
        ArrayDeque parameterQueue = specification.getParameterQueue();
        int size = parameterQueue.size();
        for (int i = 1; i <= size; i++){
            query.setParameter(i, parameterQueue.removeFirst());
        }
        return new HashSet<>(query.list());
    }

    @Override
    public boolean isExistsQuery(Specification specification) {
        return !query(specification).isEmpty();
    }
}
