package com.aminov.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client_addresses")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "street")
    private String street;

    @Column(name = "duilding")
    private String building;

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<ClientAddress> clientAddress;

    public Address(){

    }

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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<ClientAddress> getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(List<ClientAddress> clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public String toString() {
        return this.city + ", " +
               this.street + ", " +
               this.building + ", " +
               this.apartmentNumber;
    }

}
