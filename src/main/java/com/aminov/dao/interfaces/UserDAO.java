package com.aminov.dao.interfaces;

public interface UserDAO<T> extends InterfaceDAO<T> {
    T getByEmail(String email);
}
