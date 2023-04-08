package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
    public String deleteCategory(Integer id);
    public CategoryDto getCategoryById(Integer id);
    public List<CategoryDto> getAllCategory();

}
