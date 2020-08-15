package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.OrderItemDAO;
import com.aminov.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO<OrderItem> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public List<OrderItem> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select orderItem from OrderItem orderItem", OrderItem.class).list();
    }

    @Transactional
    @Override
    public void add(OrderItem orderItem) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(orderItem);
    }

    @Transactional
    @Override
    public void addItemList(List<OrderItem> itemList) {
        Session session = sessionFactory.getCurrentSession();
        for (OrderItem orderItem : itemList)
            session.persist(orderItem);
    }

    @Transactional
    @Override
    public void delete(OrderItem orderItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(orderItem);
    }

    @Transactional
    @Override
    public void edit(OrderItem orderItem) {
        Session session = sessionFactory.getCurrentSession();
        session.update(orderItem);
    }

    @Transactional
    @Override
    public OrderItem getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderItem.class, id);
    }

    @Transactional
    @Override
    public List<OrderItem> getOrderItemListByOrderId(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .getSession()
                .createQuery("select orderItem from OrderItem orderItem where orderItem.order.id=:id",
                        OrderItem.class)
                .setParameter("id", id)
                .list();
    }



}
