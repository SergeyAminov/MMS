package com.aminov.dto;

import java.util.List;

public class OrderDto {
    private int id;
    private Integer userId;
    private String address;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String deliveryStatus;
    private List<Integer> orderItemIdList;

    public OrderDto() { }

    public OrderDto(int id, Integer userId, String address, String paymentMethod, String deliveryMethod,
                    String paymentStatus, String deliveryStatus, List<Integer> orderItemIdList) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        this.orderItemIdList = orderItemIdList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<Integer> getOrderItemIdList() {
        return orderItemIdList;
    }

    public void setOrderItemIdList(List<Integer> orderItemIdList) {
        this.orderItemIdList = orderItemIdList;
    }
}
