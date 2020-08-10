package com.aminov.service;

import com.aminov.dao.UserDAO;
import com.aminov.dto.UserDto;
import com.aminov.mapper.UserMapper;
import com.aminov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UserDto>{

    private UserDAO<User> userDAO;
    private UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserDAO(UserDAO<User> userDAO){
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public List<UserDto> allItems() {
        return this.userDAO
                .allItems()
                .stream()
                .map(this.userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        this.userDAO.add(this.userMapper.toEntity(userDto));
    }

    @Transactional
    @Override
    public void delete(UserDto userDto) {
        this.userDAO.delete(this.userMapper.toEntity(userDto));
    }

    @Transactional
    @Override
    public void edit(UserDto userDto) {
        this.userDAO.edit(this.userMapper.toEntity(userDto));
    }

    @Transactional
    @Override
    public UserDto getById(int id) {
        return this.userMapper.toDto(this.userDAO.getById(id));
    }

    @Transactional
    @Override
    public UserDto getByEmail(String email) {
        return this.userMapper.toDto(this.userDAO.getByEmail(email));
    }

    @Transactional
    @Override
    public void registerNewUserAccount(UserDto userDto) throws Exception {
        if (emailExist(userDto.getEmail()))
            throw new Exception("There is an account with that email address: " + userDto.getEmail());
        userDto.setRole("ROLE_ADMIN");
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        this.userDAO.add(this.userMapper.toEntity(userDto));
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserDto userDto = this.userMapper.toDto(this.userDAO.getByEmail(email));
        if (userDto == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }

        return  new org.springframework.security.core.userdetails.User(
                userDto.getEmail(),
                userDto.getPassword().toLowerCase(),
                userDto.isEnabled(),
                userDto.isAccountNonExpired(),
                userDto.isCredentialsNonExpired(),
                userDto.isAccountNonLocked(),
                getAuthorities(userDto.getRole())
        );
    }

    private static List<GrantedAuthority> getAuthorities (String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    private boolean emailExist(String email) {
        return userDAO.getByEmail(email) != null;
    }
}
