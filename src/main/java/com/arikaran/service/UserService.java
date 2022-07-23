package com.arikaran.service;

import com.arikaran.Dto.UserDto;
import com.arikaran.entity.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(User user);
    List<UserDto> getAll();
    UserDto getById(int id);
    UserDto update(User user);
    void DeleteById(int id);

}
