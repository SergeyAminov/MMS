package com.aminov.dao.interfaces;

import com.aminov.model.Order;
import com.aminov.model.OrderItem;

import java.util.List;

public interface OrderDAO<T> extends InterfaceDAO<T> {
    List<T> getOrderListByUserId(int id);
    public void add(Order order, List<OrderItem> orderItemList);
}
