package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.OrderDAO;
import com.aminov.model.Order;
import com.aminov.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        Order finalOrder = order;
        for(OrderItem orderItem : orderItemList)
            finalOrder.setOrderItem(orderItem);
        session.persist(finalOrder);
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

    @Override
    public List<Order> getOrderListByLastWeek(){
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date startDate = calendar.getTime();
        Date endDate = new Date();

        return session
                .getSession()
                .createQuery("SELECT e FROM Order e WHERE e.date BETWEEN :start AND :end", Order.class)
                .setParameter("start", java.sql.Date.valueOf(formatter.format(startDate)))
                .setParameter("end", java.sql.Date.valueOf(formatter.format(endDate)))
                .list();
    }

    @Override
    public List<Order> getOrderListByLastMonth(){
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date startDate = calendar.getTime();
        Date endDate = new Date();

        return session
                .getSession()
                .createQuery("SELECT e FROM Order e WHERE e.date BETWEEN :start AND :end", Order.class)
                .setParameter("start", java.sql.Date.valueOf(formatter.format(startDate)))
                .setParameter("end", java.sql.Date.valueOf(formatter.format(endDate)))
                .list();
    }

}
