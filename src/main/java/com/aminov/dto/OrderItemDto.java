package com.aminov.dto;

public class OrderItemDto {

    private int id;
    int orderId;
    private String title;
    private double price;
    private String brand;
    private String color;
    private double weight;
    private double diagonal;
    private double storage;
    private int ram;

    public OrderItemDto() { }

    public OrderItemDto(int id, int orderId, String title, double price, String brand, String color,
                        double weight, double diagonal, double storage, int ram) {
        this.id = id;
        this.orderId = orderId;
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.weight = weight;
        this.diagonal = diagonal;
        this.storage = storage;
        this.ram = ram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

}
