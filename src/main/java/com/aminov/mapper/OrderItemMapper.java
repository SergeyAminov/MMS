package com.aminov.mapper;

import com.aminov.dto.OrderItemDto;
import com.aminov.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderItem toEntity(OrderItemDto orderItemDto){
        return modelMapper.map(orderItemDto, OrderItem.class);
    }

    public OrderItemDto toDto(OrderItem orderItem){
        return modelMapper.map(orderItem, OrderItemDto.class);
    }

}
