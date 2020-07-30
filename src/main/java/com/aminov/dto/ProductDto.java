package com.aminov.dto;

public class ProductDto {

    private int id;
    private String title;
    private double price;
    private ParametersDto parametersDto;
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

    public ParametersDto getParametersDto() {
        return parametersDto;
    }

    public void setParametersDto(ParametersDto parametersDto) {
        this.parametersDto = parametersDto;
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

    @Override
    public String toString() {
        return this.getTitle();
    }
}