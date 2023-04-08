package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.UserDto;
import com.microservice.blogappapis.exceptions.ResourceNotFoundException;
import com.microservice.blogappapis.models.User;
import com.microservice.blogappapis.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer id) {
        User user= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found "+id));
        user.setUserId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        User updatedUser= userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserbyId(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"+userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        if(users.isEmpty()) throw new ResourceNotFoundException("Users not Found");
        else {
            return users.stream().map(this::userToDto).toList();
        }
    }

    @Override
    public String deleteUserById(Integer userId) {
        try{
            userRepository.deleteById(userId);
        }
        catch (ResourceNotFoundException e){
            e.printStackTrace();
        }

        return "User with id"+ userId+"deleted";
    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);

//        user.setUserId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return  user;
    }
    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getUserId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
