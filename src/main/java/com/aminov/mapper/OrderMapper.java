package com.aminov.mapper;

import com.aminov.dao.interfaces.UserDAO;
import com.aminov.dto.OrderDto;

import com.aminov.model.Order;
import com.aminov.model.OrderItem;
import com.aminov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private UserDAO<User> userDAO;

    @Autowired
    public void setUserDAO(UserDAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public Order toEntity(OrderDto orderDto){

        return new Order(
                orderDto.getId(),
                this.userDAO.getById(orderDto.getUserId()),
                orderDto.getAddress(),
                orderDto.getPaymentMethod(),
                orderDto.getDeliveryMethod(),
                orderDto.getPaymentStatus(),
                orderDto.getDeliveryStatus(),
                java.sql.Date.valueOf(orderDto.getDate()),
                new ArrayList<>()
        );
    }

    public OrderDto toDto(Order order){
        List<Integer> orderItemIdList = new ArrayList<>();
        for(OrderItem orderItem : order.getOrderItemList())
            orderItemIdList.add(orderItem.getId());
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getAddress(),
                order.getPayment_method(),
                order.getDelivery_method(),
                order.getPayment_status(),
                order.getDelivery_status(),
                order.getDate().toString(),
                orderItemIdList
        );
    }
}
