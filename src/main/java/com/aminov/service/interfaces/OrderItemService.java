package com.aminov.service.interfaces;

import com.aminov.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface OrderItemService<T> extends InterfaceService<T>{
    List<T> getOrderItemDtoListByOrderId(int id);
    double getTotal(int orderId);
    List<T> getOrderItemDtoList(List<ProductDto> productDtoList);
    Map<String, Long> getTopOfItems();
}
