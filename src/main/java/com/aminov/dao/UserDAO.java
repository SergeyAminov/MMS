package com.aminov.dao;

public interface UserDAO<T> extends InterfaceDAO<T> {
    T getByEmail(String email);
}
