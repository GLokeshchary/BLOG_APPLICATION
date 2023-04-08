package com.microservice.blogappapis.controllers;

import com.microservice.blogappapis.dto.PostDto;
import com.microservice.blogappapis.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> savePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.createPost(postDto,userId,categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getAllPostsByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.getAllPostByCategory(categoryId),HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(value = "pageNumber",required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                                     @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy ){
        return new ResponseEntity<>(postService.getAllPosts(pageNumber,pageSize,sortBy),HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable Integer postId){
        return new ResponseEntity<>(postService.updatePost(postDto,postId),HttpStatus.CREATED);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId){
        return new ResponseEntity<String>(postService.deletePostById(postId),HttpStatus.OK);
    }

}
