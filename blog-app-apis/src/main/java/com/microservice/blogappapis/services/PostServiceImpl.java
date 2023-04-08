package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.PostDto;
import com.microservice.blogappapis.exceptions.ResourceNotFoundException;
import com.microservice.blogappapis.models.Category;
import com.microservice.blogappapis.models.Post;
import com.microservice.blogappapis.models.User;
import com.microservice.blogappapis.repository.CategoryRepository;
import com.microservice.blogappapis.repository.PostRepository;
import com.microservice.blogappapis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id "+userId+" no found"));
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category with id "+ categoryId+" not Found"));
        Post post=modelMapper.map(postDto,Post.class);
        post.setPostImage("default.png");
        post.setAddedDateTime(LocalDateTime.now());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost=postRepository.save(post);
        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id "+postId+" not Found"));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
        if(pageNumber==null & pageSize==null){
            List<Post> posts=postRepository.findAll();
            if (posts.isEmpty()){
                throw new ResourceNotFoundException("Posts are Not Found");
            }
            return posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).toList();
        }
       else {
            Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
            Page<Post> postPage=this.postRepository.findAll(pageable);
            List<Post> posts=postPage.getContent();
            if (posts.isEmpty()){
                throw new ResourceNotFoundException("Posts are Not Found");
            }
            return posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).toList();
        }
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id "+postId+" not Found"));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public String deletePostById(Integer postId) {
        postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id "+postId+" not Found"));
        this.postRepository.deleteById(postId);
        return "Post with id "+postId+" Deleted";
    }

    @Override //GET ALL POST FOR CATEGORY USING CATEGORY ID
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category with id "+categoryId+" not found"));
        List<Post> posts=postRepository.findByCategory(category);
        if(posts.isEmpty()) {
            throw new ResourceNotFoundException("Category List Not Found");
        }
        return posts.stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id "+userId+" not Found"));
        List<Post> posts=postRepository.findByUser(user);
        if(posts.isEmpty()) {
            throw new ResourceNotFoundException("Category List Not Found");
        }
        return posts.stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
