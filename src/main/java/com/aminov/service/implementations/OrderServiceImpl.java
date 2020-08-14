package com.aminov.service.implementations;

import com.aminov.dao.interfaces.OrderDAO;
import com.aminov.dto.OrderDto;
import com.aminov.dto.OrderItemDto;
import com.aminov.mapper.OrderMapper;
import com.aminov.model.Order;
import com.aminov.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService<OrderDto> {

    private OrderDAO<Order> orderDAO;
    private OrderMapper orderMapper;

    @Autowired
    public void setOrderDAO(OrderDAO<Order> orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Transactional
    @Override
    public List<OrderDto> allItems() {
        return this.orderDAO
                .allItems()
                .stream()
                .map(this.orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(OrderDto orderDto) {
        this.orderDAO.add(this.orderMapper.toEntity(orderDto));
    }

    @Transactional
    @Override
    public void add(OrderDto orderDto, List<OrderItemDto> orderItemDtoList){
        List<Integer> orderItemIdList = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtoList)
            orderItemIdList.add(orderItemDto.getId());
        orderDto.setOrderItemIdList(orderItemIdList);
        this.add(orderDto);
    }

    @Transactional
    @Override
    public void delete(OrderDto orderDto) {
        this.orderDAO.delete(this.orderMapper.toEntity(orderDto));
    }

    @Transactional
    @Override
    public void edit(OrderDto orderDto) {
        this.orderDAO.edit(this.orderMapper.toEntity(orderDto));
    }

    @Transactional
    @Override
    public OrderDto getById(int id) {
        return this.orderMapper.toDto(this.orderDAO.getById(id));
    }
}
