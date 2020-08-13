package com.aminov.service.interfaces;

import java.util.List;

public interface OrderItemService<T> extends InterfaceService<T>{
    List<T> getOrderItemListByOrderId(int id);
    double getOrderPrice(int orderId);
}
