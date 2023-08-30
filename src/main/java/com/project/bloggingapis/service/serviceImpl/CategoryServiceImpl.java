package com.project.bloggingapis.service.serviceImpl;

import com.project.bloggingapis.dto.CategoryDTO;
import com.project.bloggingapis.dto.UserDTO;
import com.project.bloggingapis.exceptions.ResourceNotFoundException;
import com.project.bloggingapis.model.Category;
import com.project.bloggingapis.model.User;
import com.project.bloggingapis.repository.CategoryRepository;
import com.project.bloggingapis.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryRepository.save(modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId : ",categoryId));
        category.setCategoryDescription(category.getCategoryDescription());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId : ",categoryId));

        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId : ",categoryId));

        categoryRepository.delete(category);
    }
}
