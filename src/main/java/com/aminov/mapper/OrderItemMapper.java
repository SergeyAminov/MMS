package com.aminov.mapper;

import com.aminov.dto.OrderItemDto;
import com.aminov.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toEntity(OrderItemDto orderItemDto){
        return new OrderItem(
                orderItemDto.getId(),
                null,
                orderItemDto.getTitle(),
                orderItemDto.getPrice(),
                orderItemDto.getBrand(),
                orderItemDto.getColor(),
                orderItemDto.getWeight(),
                orderItemDto.getDiagonal(),
                orderItemDto.getStorage(),
                orderItemDto.getRam()
        );
    }

    public OrderItemDto toDto(OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getOrder().getId(),
                orderItem.getTitle(),
                orderItem.getPrice(),
                orderItem.getBrand(),
                orderItem.getColor(),
                orderItem.getWeight(),
                orderItem.getDiagonal(),
                orderItem.getStorage(),
                orderItem.getRam()
        );
    }

}
