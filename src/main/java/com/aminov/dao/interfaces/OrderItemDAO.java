package com.aminov.dao.interfaces;

import java.util.List;

public interface OrderItemDAO<T> extends InterfaceDAO<T>{
    List<T> getOrderItemListByOrderId(int id);
    void addItemList(List<T> itemList);
    List<Object[]> getTopOfItems();
}
