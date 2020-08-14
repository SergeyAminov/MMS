package com.aminov.service.implementations;

import com.aminov.dao.interfaces.OrderItemDAO;
import com.aminov.dto.OrderItemDto;
import com.aminov.dto.ProductDto;
import com.aminov.mapper.OrderItemMapper;
import com.aminov.model.OrderItem;
import com.aminov.service.interfaces.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService<OrderItemDto> {
    private OrderItemDAO<OrderItem> orderItemDAO;
    private OrderItemMapper orderItemMapper;

    @Autowired
    public void setOrderItemDAO(OrderItemDAO<OrderItem> orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    @Autowired
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Transactional
    @Override
    public List<OrderItemDto> allItems() {
        return this.orderItemDAO
                .allItems()
                .stream()
                .map(this.orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(OrderItemDto orderItemDto) {
        this.orderItemDAO.add(this.orderItemMapper.toEntity(orderItemDto));
    }

    @Transactional
    @Override
    public void delete(OrderItemDto orderItemDto) {
        this.orderItemDAO.delete(this.orderItemMapper.toEntity(orderItemDto));
    }

    @Transactional
    @Override
    public void edit(OrderItemDto orderItemDto) {
        this.orderItemDAO.edit(this.orderItemMapper.toEntity(orderItemDto));
    }

    @Transactional
    @Override
    public OrderItemDto getById(int id) {
        return this.orderItemMapper.toDto(this.orderItemDAO.getById(id));
    }

    @Transactional
    @Override
    public List<OrderItemDto> getOrderItemDtoList(List<ProductDto> productDtoList){
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        int newID = 0;
        for(ProductDto productDto : productDtoList) {
            orderItemDtoList.add(new OrderItemDto(
                    newID,
                    newID,
                    productDto.getTitle(),
                    productDto.getPrice(),
                    productDto.getBrand(),
                    productDto.getColor(),
                    productDto.getWeight(),
                    productDto.getDiagonal(),
                    productDto.getStorage(),
                    productDto.getRam()
            ));
        }
        return orderItemDtoList;
    }

    @Transactional
    @Override
    public List<OrderItemDto> getOrderItemDtoListByOrderId(int id) {
        return this.orderItemDAO
                .getOrderItemListByOrderId(id)
                .stream()
                .map(this.orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public double getOrderPrice(int orderId) {
        double orderPrice = 0;
        for (OrderItemDto orderItemDto : this.getOrderItemDtoListByOrderId(orderId))
            orderPrice += orderItemDto.getPrice();
        return orderPrice;
    }
}
