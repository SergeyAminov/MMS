package com.aminov.service.interfaces;

import com.aminov.dto.OrderItemDto;

import java.util.List;
import java.util.Map;

public interface OrderService<T> extends InterfaceService<T> {
    void add(T t, List<OrderItemDto> orderItemDtoList);
    public Map<T, List<OrderItemDto>> getOrderMap();
}
