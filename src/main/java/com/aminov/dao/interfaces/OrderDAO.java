package com.aminov.dao.interfaces;

import java.util.List;

public interface OrderDAO<T> extends InterfaceDAO<T> {
    List<T> getOrderListByUserId(int id);
}
