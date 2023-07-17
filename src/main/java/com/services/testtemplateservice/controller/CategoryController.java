package com.services.testtemplateservice.controller;


import com.services.testtemplateservice.dto.CategoryRequest;
import com.services.testtemplateservice.dto.CategoryResponse;
import com.services.testtemplateservice.model.Category;
import com.services.testtemplateservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
        List<CategoryResponse>  categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.addCategory(categoryRequest);
        return ResponseEntity.ok().build();

    }

}
