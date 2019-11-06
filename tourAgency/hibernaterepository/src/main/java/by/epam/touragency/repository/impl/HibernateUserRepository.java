package by.epam.touragency.repository.impl;

import by.epam.touragency.entity.User;
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
public class HibernateUserRepository  implements Repository<User> {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session(){
        return sessionFactory.openSession();
    }

    @Override
    public void add(User item, Specification specification) {
        Session session = session();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User entity, Specification specification) {
        Query<User> query = session().createQuery(specification.sqlQuery(), User.class);
        ArrayDeque parameterQueue = specification.getParameterQueue();
        for (int i = 1; i <= parameterQueue.size(); i++){
            query.setParameter(i, parameterQueue.getFirst());
        }
    }

    @Override
    public void remove(User entity, Specification specification) {

    }

    @Override
    public Set<User> query(Specification specification)  {
        Query<User> query = session().createQuery(specification.sqlQuery(), User.class);
        ArrayDeque parameterQueue = specification.getParameterQueue();
        for (int i = 1; i <= parameterQueue.size(); i++){
            query.setParameter(i, parameterQueue.getFirst());
        }
        return new HashSet<>(query.list());
    }

    @Override
    public boolean isExistsQuery(Specification specification){
        return false;
    }
}
