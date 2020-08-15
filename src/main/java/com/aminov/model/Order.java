package com.aminov.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "payment_method")
    private String payment_method;

    @JoinColumn(name = "delivery_method")
    private String delivery_method;

    @JoinColumn(name = "payment_status")
    private String payment_status;

    @JoinColumn(name = "delivery_status")
    private String delivery_status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public Order(int id, User user, String address, String payment_method, String delivery_method,
                 String payment_status, String delivery_status, List<OrderItem> orderItemList) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.payment_method = payment_method;
        this.delivery_method = delivery_method;
        this.payment_status = payment_status;
        this.delivery_status = delivery_status;
        this.orderItemList = orderItemList;
    }

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getDelivery_method() {
        return delivery_method;
    }

    public void setDelivery_method(String delivery_method) {
        this.delivery_method = delivery_method;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void setOrderItem(OrderItem orderItem){
        this.orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem){
        this.orderItemList.remove(orderItem);
        orderItem.setOrder(null);
    }

}
