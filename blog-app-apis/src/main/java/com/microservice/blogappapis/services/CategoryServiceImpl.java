package com.microservice.blogappapis.services;

import com.microservice.blogappapis.dto.CategoryDto;
import com.microservice.blogappapis.exceptions.ResourceNotFoundException;
import com.microservice.blogappapis.models.Category;
import com.microservice.blogappapis.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private  final CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto, Category.class);
        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category with id "+" "+categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory= categoryRepository.save(category);
        CategoryDto map = modelMapper.map(category, CategoryDto.class);
        return map;
    }

    @Override
    public String deleteCategory(Integer id) {

        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id "+ id));
        categoryRepository.deleteById(id);
        return "Category with Id "+id+" Deleted";
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id "+id+" not Found"));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            throw new ResourceNotFoundException("Categories not found");
        }
        else {
         return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        }
    }
}
