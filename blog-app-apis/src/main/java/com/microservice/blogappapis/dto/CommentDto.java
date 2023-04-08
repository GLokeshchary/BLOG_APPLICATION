package com.microservice.blogappapis.dto;

import com.microservice.blogappapis.models.Post;
import com.microservice.blogappapis.models.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String commentContent;
    private UserDto user;
}
