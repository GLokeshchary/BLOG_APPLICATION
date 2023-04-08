package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.CommentDto;

public interface CommentService {
    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    public String deleteComment(Integer commentId);
}
