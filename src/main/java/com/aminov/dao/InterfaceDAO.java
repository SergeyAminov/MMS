package com.aminov.dao;

import java.util.List;

public interface InterfaceDAO<T> {
    List<T> allItems();
    void add(T t);
    void delete(T t);
    void edit(T t);
    T getById(int id);
}
