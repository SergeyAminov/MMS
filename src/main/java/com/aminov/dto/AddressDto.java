package com.aminov.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddressDto {

    private int id;

    @NotEmpty
    @Size(min = 2)
    private String country;
    private String city;
    private String street;
    private int postcode;
    private int building;
    private int apartNumber;
    private Integer userId;

    public AddressDto() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getApartNumber() {
        return apartNumber;
    }

    public void setApartNumber(int apartNumber) {
        this.apartNumber = apartNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return this.country + ", " + this.city + " " + this.postcode + ", " + this.street + " st. " + this.building + ", " + this.apartNumber;
    }
}
