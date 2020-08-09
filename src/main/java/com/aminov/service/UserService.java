package com.aminov.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService<T> extends InterfaceService<T>, UserDetailsService {

    T getByEmail(String email);

    public void registerNewUserAccount(T t) throws Exception;

}
