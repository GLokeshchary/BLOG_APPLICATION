package com.microservice.blogappapis.controllers;

import com.microservice.blogappapis.dto.CommentDto;
import com.microservice.blogappapis.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,@PathVariable Integer userId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId,userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId){
        return new ResponseEntity<>(commentService.deleteComment(commentId),HttpStatus.OK);
    }
}
