package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.CommentDto;
import com.microservice.blogappapis.exceptions.ResourceNotFoundException;
import com.microservice.blogappapis.models.Comment;
import com.microservice.blogappapis.models.Post;
import com.microservice.blogappapis.models.User;
import com.microservice.blogappapis.repository.CommentRepository;
import com.microservice.blogappapis.repository.PostRepository;
import com.microservice.blogappapis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
       Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id "+postId+" not Found"));
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with Id "+userId+" Not Found"));
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        this.commentRepository.save(comment);
        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public String deleteComment(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment with id "+commentId+" Not Found"));
        commentRepository.deleteById(commentId);
        return "Comment with Id "+commentId+" Deleted";
    }
}
