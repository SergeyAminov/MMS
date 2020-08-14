package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.OrderDAO;
import com.aminov.model.Order;
import com.aminov.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO<Order> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select order from Order order", Order.class).list();
    }

    @Override
    public void add(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
    }

    @Override
    public void add(Order order, List<OrderItem> orderItemList) {
        Session session = sessionFactory.getCurrentSession();
        order.setOrderItemList(orderItemList);
        session.persist(order);
    }

    @Override
    public void delete(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(order);
    }

    @Override
    public void edit(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    @Override
    public Order getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Order.class, id);
    }

    @Override
    public List<Order> getOrderListByUserId(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .getSession()
                .createQuery("select order from Order order where order.user.id=:id", Order.class)
                .setParameter("id", id)
                .list();
    }
}
