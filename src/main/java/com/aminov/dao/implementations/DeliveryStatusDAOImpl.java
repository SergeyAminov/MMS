package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.DeliveryStatusDAO;
import com.aminov.model.DeliveryStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryStatusDAOImpl implements DeliveryStatusDAO<DeliveryStatus> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<DeliveryStatus> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select deliveryStatus from DeliveryStatus deliveryStatus", DeliveryStatus.class).list();
    }

    @Override
    public void add(DeliveryStatus deliveryStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(deliveryStatus);
    }

    @Override
    public void delete(DeliveryStatus deliveryStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(deliveryStatus);
    }

    @Override
    public void edit(DeliveryStatus deliveryStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.update(deliveryStatus);
    }

    @Override
    public DeliveryStatus getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DeliveryStatus.class, id);
    }
}
