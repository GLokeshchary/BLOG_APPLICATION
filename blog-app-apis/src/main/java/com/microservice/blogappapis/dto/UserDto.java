package com.microservice.blogappapis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min=4,message = "Username must be min of 4 chars !!")
    private String name;
    @NotEmpty
    @Email(message = "Email address is not Valid !!")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 7,message = "Password must be min of {min} chras and max of {max} chars !!")
    private String password;
    @NotEmpty
    private String about;
}
