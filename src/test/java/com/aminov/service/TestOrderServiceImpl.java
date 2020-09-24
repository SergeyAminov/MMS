package com.aminov.service;
/*
import com.aminov.dao.implementations.UserDAOImpl;
import com.aminov.dto.OrderDto;
import com.aminov.dto.OrderItemDto;
import com.aminov.mapper.OrderItemMapper;
import com.aminov.mapper.OrderMapper;
import com.aminov.model.Order;
import com.aminov.model.OrderItem;
import com.aminov.model.User;
import com.aminov.service.implementations.OrderItemServiceImpl;
import com.aminov.service.implementations.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderServiceImpl {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private User user;

    @Mock
    private OrderItemServiceImpl orderItemService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderItemMapper orderItemMapper;

    @Mock
    private UserDAOImpl userDAO;

    @Before
    public void before(){
        when(orderMapper.toEntity(any())).thenReturn(new Order());
        when(orderItemMapper.toEntity(any())).thenReturn(new OrderItem());
        when(orderItemService.getTotal(any())).thenReturn(1.0);

        user.setId(1);

        when(userDAO.getById(1)).thenReturn(user);
        when(user.getTotal()).thenReturn(1.0);
    }

    @Test
    public void addOrder(){

        OrderDto orderDto = new OrderDto();
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        orderItemDtoList.add(new OrderItemDto());
        orderItemDtoList.add(new OrderItemDto());
        orderItemDtoList.add(new OrderItemDto());

        orderService.add(orderDto, orderItemDtoList);

        verify(orderService, times(1));
    }


}
 */
