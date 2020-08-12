package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.DeliveryMethodDAO;
import com.aminov.model.DeliveryMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryMethodDAOImpl implements DeliveryMethodDAO<DeliveryMethod> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<DeliveryMethod> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select deliveryMethod from DeliveryMethod deliveryMethod", DeliveryMethod.class).list();
    }

    @Override
    public void add(DeliveryMethod deliveryMethod) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(deliveryMethod);
    }

    @Override
    public void delete(DeliveryMethod deliveryMethod) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(deliveryMethod);
    }

    @Override
    public void edit(DeliveryMethod deliveryMethod) {
        Session session = sessionFactory.getCurrentSession();
        session.update(deliveryMethod);
    }

    @Override
    public DeliveryMethod getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DeliveryMethod.class, id);
    }
}
