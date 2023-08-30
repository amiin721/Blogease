package com.project.bloggingapis.service;

import com.project.bloggingapis.dto.CategoryDTO;
import com.project.bloggingapis.dto.UserDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    CategoryDTO getCategoryById(Integer categoryId);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(Integer categoryId);
}
