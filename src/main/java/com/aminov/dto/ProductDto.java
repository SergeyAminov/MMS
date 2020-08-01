package com.aminov.dto;

public class ProductDto {

    private int id;
    private String title;
    private double price;
    private int count;
    private int categoryId;
    private int parametersId;

    public ProductDto() {}

    public ProductDto(
            int id,
            String title,
            double price,
            int count,
            int categoryId,
            int parametersId
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
        this.categoryId = categoryId;
        this.parametersId = parametersId;
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getParametersId() {
        return parametersId;
    }

    public void setParametersId(int parametersId) {
        this.parametersId = parametersId;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}