package com.aminov.dto;

import java.util.List;

public class PaymentStatusDto {
    private int id;
    private String title;
    private List<Integer> orderIdList;

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

    public List<Integer> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList) {
        this.orderIdList = orderIdList;
    }

    @Override
    public String toString(){
        return this.title;
    }
}
