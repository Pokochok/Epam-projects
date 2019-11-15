package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.Tour;
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
public class HibernateTourRepository implements Repository<Tour> {

    private Session session;

    @Autowired
    public HibernateTourRepository(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public void add(Tour tour, Specification specification) {
        session.beginTransaction();
        session.save(tour);
        session.getTransaction().commit();
    }

    @Override
    public void update(Tour entity, Specification specification) {
        session.beginTransaction();
        session.clear();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void remove(Tour entity, Specification specification) {
        session.beginTransaction();
        session.clear();
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public Set<Tour> query(Specification specification) {
        Query<Tour> query = session.createQuery(specification.sqlQuery(), Tour.class);
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
