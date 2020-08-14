package com.aminov.service.interfaces;

import com.aminov.dto.ProductDto;

import java.util.List;

public interface OrderItemService<T> extends InterfaceService<T>{
    List<T> getOrderItemDtoListByOrderId(int id);
    double getOrderPrice(int orderId);
    List<T> getOrderItemDtoList(List<ProductDto> productDtoList);
}
