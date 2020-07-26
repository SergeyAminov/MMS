package com.aminov.model;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "weight")
    private double weight;

    @Column(name = "diagonal")
    private double diagonal;

    @Column(name = "storage")
    private double storage;

    @Column(name = "ram")
    private int ram;

    @OneToOne(optional = false, mappedBy="parameters")
    public Product product;

    public Parameters() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}