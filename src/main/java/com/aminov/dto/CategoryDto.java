package com.aminov.dto;

import java.util.List;

public class CategoryDto {

    private int id;
    private String title;
    private List<ProductDto> productDtoList;

    public CategoryDto() {}

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

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    @Override public String toString(){
        return this.title;
    }

}
