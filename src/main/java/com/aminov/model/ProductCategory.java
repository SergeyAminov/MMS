package com.aminov.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class ProductCategory {

    @Id
    @Column(name = "category")
    private String category;

    public ProductCategory() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
