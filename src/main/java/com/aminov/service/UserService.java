package com.aminov.service;

public interface UserService<T> extends InterfaceService<T>{

    T getByEmail(String email);

}
