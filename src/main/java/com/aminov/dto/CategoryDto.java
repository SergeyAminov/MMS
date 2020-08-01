package com.aminov.dto;

import java.util.List;

public class CategoryDto {

    private int id;
    private String title;
    private List<Integer> productIdList;

    public CategoryDto() {}

    public CategoryDto(int id, String title, List<Integer> productIdList) {
        this.id = id;
        this.title = title;
        this.productIdList = productIdList;
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

    public List<Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Integer> productIdList) {
        this.productIdList = productIdList;
    }

    @Override public String toString(){
        return this.title;
    }

}
