package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.PostDto;
import com.microservice.blogappapis.models.Post;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    public PostDto updatePost(PostDto postDto,Integer id);
    public List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
    public PostDto getPostById(Integer id);
    public String deletePostById(Integer id);
    public List<PostDto> getAllPostByCategory(Integer categoryId);
    public List<PostDto> getAllPostsByUser(Integer userId);
    public List<PostDto> searchPosts(String keyword);
}
