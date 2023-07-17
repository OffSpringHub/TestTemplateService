package com.services.testtemplateservice.service;


import com.services.testtemplateservice.dto.CategoryRequest;
import com.services.testtemplateservice.dto.CategoryResponse;
import com.services.testtemplateservice.model.Category;
import com.services.testtemplateservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return  categories.stream().map(this::mapToCategoryResponse).toList();
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category addCategory(CategoryRequest categoryRequest) {
        Category category = mapToCategory(categoryRequest);
        return categoryRepository.save(category);
    }

    private Category mapToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }
}
