package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.PaymentMethodDAO;
import com.aminov.model.PaymentMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentMethodDAOImpl implements PaymentMethodDAO<PaymentMethod> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PaymentMethod> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select paymentMethod from PaymentMethod paymentMethod", PaymentMethod.class).list();
    }

    @Override
    public void add(PaymentMethod paymentMethod) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(paymentMethod);
    }

    @Override
    public void delete(PaymentMethod paymentMethod) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(paymentMethod);
    }

    @Override
    public void edit(PaymentMethod paymentMethod) {
        Session session = sessionFactory.getCurrentSession();
        session.update(paymentMethod);
    }

    @Override
    public PaymentMethod getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(PaymentMethod.class, id);
    }
}
