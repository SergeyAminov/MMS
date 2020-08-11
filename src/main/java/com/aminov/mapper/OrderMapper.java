package com.aminov.mapper;

import com.aminov.dto.OrderDto;
import com.aminov.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Order toEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }

    public OrderDto toDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }
}
