package com.aminov.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private int id;
    private UserDto userId;
    private Integer addressId;
    private Integer paymentMethodId;
    private Integer deliveryMethodId;
    private Integer paymentStatusId;
    private Integer deliveryStatusId;
    private List<Integer> orderItemIdList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Integer getDeliveryMethodId() {
        return deliveryMethodId;
    }

    public void setDeliveryMethodId(Integer deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    public Integer getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(Integer paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public Integer getDeliveryStatusId() {
        return deliveryStatusId;
    }

    public void setDeliveryStatusId(Integer deliveryStatusId) {
        this.deliveryStatusId = deliveryStatusId;
    }

    public List<Integer> getOrderItemIdList() {
        return orderItemIdList;
    }

    public void setOrderItemIdList(List<Integer> orderItemIdList) {
        this.orderItemIdList = orderItemIdList;
    }
}
