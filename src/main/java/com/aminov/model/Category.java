package com.aminov.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category")
    private String category;

    @OneToOne(optional = false, mappedBy="category")
    public Product product;

}