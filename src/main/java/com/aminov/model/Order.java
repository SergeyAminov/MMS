package com.aminov.model;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "payment_method")
    private PaymentMethod payment_method;

    @ManyToOne
    @JoinColumn(name = "delivery_method")
    private DeliveryMethod delivery_method;

    @ManyToOne
    @JoinColumn(name = "payment_status")
    private PaymentStatus payment_status;

    @ManyToOne
    @JoinColumn(name = "delivery_status")
    private DeliveryStatus delivery_status;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public DeliveryMethod getDelivery_method() {
        return delivery_method;
    }

    public void setDelivery_method(DeliveryMethod delivery_method) {
        this.delivery_method = delivery_method;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public DeliveryStatus getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(DeliveryStatus delivery_status) {
        this.delivery_status = delivery_status;
    }
}
