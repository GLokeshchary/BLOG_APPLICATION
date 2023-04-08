package com.microservice.blogappapis.controllers;

import com.microservice.blogappapis.dto.UserDto;
import com.microservice.blogappapis.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        UserDto saveduser=userServiceImpl.createUser(userDto);
        return new ResponseEntity<>(saveduser, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos=userServiceImpl.getAllUsers();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
       UserDto usedto1=userServiceImpl.updateUser(userDto,userId);
       return new ResponseEntity<>(usedto1,HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto=userServiceImpl.getUserbyId(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId){
        String message=userServiceImpl.deleteUserById(userId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
