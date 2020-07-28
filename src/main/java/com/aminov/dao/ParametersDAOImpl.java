package com.aminov.dao;

import com.aminov.model.Parameters;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParametersDAOImpl implements ParametersDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Parameters parameters) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(parameters);
    }

    @Override
    public void delete(Parameters parameters) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(parameters);
    }

    @Override
    public void edit(Parameters parameters) {
        Session session = sessionFactory.getCurrentSession();
        session.update(parameters);
    }

    @Override
    public Parameters getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Parameters.class, id);
    }
}
