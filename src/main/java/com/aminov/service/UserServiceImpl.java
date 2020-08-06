package com.aminov.service;

import com.aminov.dao.UserDAO;
import com.aminov.dto.UserDto;
import com.aminov.mapper.UserMapper;
import com.aminov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UserDto> {

    private UserDAO<User> userDAO;
    private UserMapper userMapper;

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

    @Override
    public UserDto getByEmail(String email) {
        return this.userMapper.toDto(this.userDAO.getByEmail(email));
    }
}
