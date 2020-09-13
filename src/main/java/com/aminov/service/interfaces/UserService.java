package com.aminov.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService<T> extends InterfaceService<T>, UserDetailsService {
    T getByEmail(String email);
    void registerNewUserAccount(T t) throws Exception;
    List<T> getTopOfUsers();
}
