package com.aminov.service;

public interface UserService<T> extends InterfaceService<T>{

    T getByEmail(String email);

    public void registerNewUserAccount(T t) throws Exception;

}
