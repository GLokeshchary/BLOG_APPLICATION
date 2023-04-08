package com.microservice.blogappapis.dto;

import com.microservice.blogappapis.models.Category;
import com.microservice.blogappapis.models.Comment;
import com.microservice.blogappapis.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private String postImage;
    private LocalDateTime addedDateTime;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comments=new ArrayList<>();
}
