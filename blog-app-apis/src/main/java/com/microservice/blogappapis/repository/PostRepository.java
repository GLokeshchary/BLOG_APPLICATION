package com.microservice.blogappapis.repository;

import com.microservice.blogappapis.dto.PostDto;
import com.microservice.blogappapis.models.Category;
import com.microservice.blogappapis.models.Post;
import com.microservice.blogappapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    public List<Post> findByCategory(Category category);

    public List<Post> findByUser(User user);
}
