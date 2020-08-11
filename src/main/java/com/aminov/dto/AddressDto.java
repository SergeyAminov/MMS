package com.aminov.dto;

import java.util.List;

public class AddressDto {

    private int id;
    private String country;
    private String city;
    private String street;
    private int postcode;
    private int building;
    private int apartNumber;
    private UserDto userDto;
    private List<OrderDto> orderDtoList;

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }

    @Override
    public String toString() {
        return this.city + ", " + this.postcode + ", " + this.street + " " + this.building + ", " + this.apartNumber;
    }
}
