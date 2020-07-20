package com.aminov.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category")
    private String category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<ProductCategory> productCategory;

    public Category() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ProductCategory> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<ProductCategory> productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
