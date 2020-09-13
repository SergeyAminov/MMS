package com.aminov.dao.interfaces;

import java.util.List;

public interface UserDAO<T> extends InterfaceDAO<T> {
    T getByEmail(String email);
    List<T> getTopOfUsers();
}
