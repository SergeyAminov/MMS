package com.aminov.dto;

import java.util.List;

public class PaymentStatusDto {
    private int id;
    private String title;
    private List<OrderDto> orderDtoList;

    public PaymentStatusDto() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }

    @Override
    public String toString(){
        return this.title;
    }
}
