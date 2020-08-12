package com.aminov.service.interfaces;

import java.util.List;

public interface InterfaceService<T> {
    List<T> allItems();
    void add(T t);
    void delete(T t);
    void edit(T t);
    T getById(int id);
}
