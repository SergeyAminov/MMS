package com.aminov.dto;

public class ProductDto {

    private int id;
    private String title;
    private double price;
    private int parametersId;
    private int categoryId;
    private int count;

    public ProductDto() {}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getParametersId() {
        return parametersId;
    }

    public void setParametersId(int parametersId) {
        this.parametersId = parametersId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}