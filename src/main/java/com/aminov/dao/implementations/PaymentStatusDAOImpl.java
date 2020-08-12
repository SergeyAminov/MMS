package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.PaymentStatusDAO;
import com.aminov.model.PaymentStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentStatusDAOImpl implements PaymentStatusDAO<PaymentStatus> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PaymentStatus> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select paymentStatus from PaymentStatus paymentStatus", PaymentStatus.class).list();
    }

    @Override
    public void add(PaymentStatus paymentStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(paymentStatus);
    }

    @Override
    public void delete(PaymentStatus paymentStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(paymentStatus);
    }

    @Override
    public void edit(PaymentStatus paymentStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.update(paymentStatus);
    }

    @Override
    public PaymentStatus getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(PaymentStatus.class, id);
    }

}
