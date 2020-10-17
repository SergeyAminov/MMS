package com.aminov.service.implementations;

import com.aminov.dao.interfaces.OrderDAO;
import com.aminov.dao.interfaces.UserDAO;
import com.aminov.dto.OrderDto;
import com.aminov.dto.OrderItemDto;
import com.aminov.mapper.OrderItemMapper;
import com.aminov.mapper.OrderMapper;
import com.aminov.model.Order;
import com.aminov.model.User;
import com.aminov.service.interfaces.OrderItemService;
import com.aminov.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService<OrderDto> {

    private OrderItemService<OrderItemDto> orderItemService;
    private OrderDAO<Order> orderDAO;
    private UserDAO<User> userDAO;
    private OrderItemMapper orderItemMapper;
    private OrderMapper orderMapper;

    @Autowired
    public void setUserDAO(UserDAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Autowired
    public void setOrderDAO(OrderDAO<Order> orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setOrderItemService(OrderItemService<OrderItemDto> orderItemService) {
        this.orderItemService = orderItemService;
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

    /**
     * Add the new Order to the database.
     *
     * @param orderDto an object with information about customer, payment- and delivery details etc.
     * @param orderItemDtoList contains the list of items in Order
     */
    @Transactional
    @Override
    public void add(OrderDto orderDto, List<OrderItemDto> orderItemDtoList){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        /* Due to unknown reason program sets current date as the previous day that's why we add one here to fix it */
        calendar.add(Calendar.DAY_OF_YEAR, +1);

        Date date = calendar.getTime();
        orderDto.setDate(formatter.format(date));
        this.orderDAO.add(
                this.orderMapper.toEntity(orderDto),
                orderItemDtoList.stream().map(this.orderItemMapper::toEntity).collect(Collectors.toList())
        );
        double orderTotal = orderItemService.getTotal(orderItemDtoList);
        double currentTotal = this.userDAO.getById(orderDto.getUserId()).getTotal();
        this.userDAO.getById(orderDto.getUserId()).setTotal(orderTotal + currentTotal);
    }

    @Transactional
    @Override
    public Map<OrderDto, List<OrderItemDto>> getOrderMap(){
        Map<OrderDto, List<OrderItemDto>> orderMap = new HashMap<>();
        for (OrderDto orderDto : this.allItems())
            orderMap.put(
                    orderDto,
                    this.orderItemService.getOrderItemDtoListByOrderId(orderDto.getId())
            );
        return orderMap;
    }

    @Transactional
    @Override
    public Map<OrderDto, List<OrderItemDto>> getOrderMap(int id){
        Map<OrderDto, List<OrderItemDto>> orderMap = new HashMap<>();
        for (OrderDto orderDto : this.allItems())
            if (orderDto.getUserId() == id){
                orderMap.put(
                        orderDto,
                        this.orderItemService.getOrderItemDtoListByOrderId(orderDto.getId())
                );
            }
        return orderMap;
    }

    @Transactional
    @Override
    public void delete(OrderDto orderDto) {
        this.orderDAO.delete(this.orderMapper.toEntity(orderDto));
    }

    @Transactional
    @Override
    public void edit(OrderDto orderDto) {
        this.orderDAO.getById(orderDto.getId()).setDelivery_status(orderDto.getDeliveryStatus());
        this.orderDAO.getById(orderDto.getId()).setPayment_status(orderDto.getPaymentStatus());
        this.orderDAO.edit(this.orderDAO.getById(orderDto.getId()));
    }

    @Transactional
    @Override
    public OrderDto getById(int id) {
        return this.orderMapper.toDto(this.orderDAO.getById(id));
    }

    @Transactional
    @Override
    public List<OrderDto> getOrderDtoListByLastMonth(){
        return this.orderDAO
                .getOrderListByLastMonth()
                .stream()
                .map(this.orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public double getAmountByLastMonth(){
        double amount = 0;

        List<OrderDto> orderDtoList = this.getOrderDtoListByLastMonth();

        for (OrderDto orderDto : orderDtoList){
            amount += this.orderItemService.getTotal(orderDto.getId());
        }
        return amount;
    }

    @Transactional
    @Override
    public List<OrderDto> getOrderDtoListByLastWeek(){
        return this.orderDAO
                .getOrderListByLastWeek()
                .stream()
                .map(this.orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public double getAmountByLastWeek(){
        double amount = 0;

        List<OrderDto> orderDtoList = this.getOrderDtoListByLastWeek();

        for (OrderDto orderDto : orderDtoList){
            amount += this.orderItemService.getTotal(orderDto.getId());
        }
        return amount;
    }

    @Override
    public List<OrderDto> getOrderDtoListByUserId(int id) {
        return this.orderDAO
                .getOrderListByUserId(id)
                .stream()
                .map(this.orderMapper::toDto)
                .collect(Collectors.toList());
    }

}
