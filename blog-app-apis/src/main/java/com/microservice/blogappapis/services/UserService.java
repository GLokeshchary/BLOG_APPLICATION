package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto,Integer id);
    public UserDto getUserbyId(Integer userId);
    public List<UserDto> getAllUsers();
    public String deleteUserById(Integer userId);

}
