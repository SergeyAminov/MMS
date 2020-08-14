package com.aminov.service.interfaces;

import com.aminov.dto.OrderDto;
import com.aminov.dto.OrderItemDto;

import java.util.List;

public interface OrderService<T> extends InterfaceService<T> {
    void add(OrderDto orderDto, List<OrderItemDto> orderItemDtoList);
}
