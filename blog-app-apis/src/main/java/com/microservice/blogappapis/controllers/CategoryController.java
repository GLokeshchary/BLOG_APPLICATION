package com.microservice.blogappapis.controllers;

import com.microservice.blogappapis.dto.CategoryDto;
import com.microservice.blogappapis.services.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private  final CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=categoryServiceImpl.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity< List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryServiceImpl.getAllCategory(),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryServiceImpl.getCategoryById(categoryId),HttpStatus.OK);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable Integer categoryId,@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryServiceImpl.updateCategory(categoryDto,categoryId),HttpStatus.CREATED);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryServiceImpl.deleteCategory(categoryId),HttpStatus.OK);
    }
}

