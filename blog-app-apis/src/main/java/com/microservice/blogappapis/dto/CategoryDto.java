package com.microservice.blogappapis.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    @NotEmpty
    @Size(min=3,max=10,message = "category must contain at least 3 chars and at most 10 chars !!")
    private String categoryTitle;
    @NotEmpty
    @Size(max=1000,message = "Description should less than 1000 chars !!")
    private  String categoryDescription;
}
