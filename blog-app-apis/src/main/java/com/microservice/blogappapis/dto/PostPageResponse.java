package com.microservice.blogappapis.dto;

import com.microservice.blogappapis.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPageResponse {
    private List<Post> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private boolean lastPage;

}
