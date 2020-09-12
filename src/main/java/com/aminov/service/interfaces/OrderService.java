package com.aminov.service.interfaces;

import com.aminov.dto.OrderItemDto;

import java.util.List;
import java.util.Map;

public interface OrderService<T> extends InterfaceService<T> {
    void add(T t, List<OrderItemDto> orderItemDtoList);
    Map<T, List<OrderItemDto>> getOrderMap();
    Map<T, List<OrderItemDto>> getOrderMap(int id);
    List<T> getOrderDtoListByLastMonth();
    List<T> getOrderDtoListByLastWeek();
    double getAmountByLastMonth();
    double getAmountByLastWeek();
}
