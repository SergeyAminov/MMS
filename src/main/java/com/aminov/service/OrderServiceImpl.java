package com.aminov.service;

import com.aminov.dao.OrderDAO;
import com.aminov.dto.OrderDto;
import com.aminov.mapper.OrderMapper;
import com.aminov.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
