package com.project.bloggingapis.controller;

import com.project.bloggingapis.dto.CategoryDTO;
import com.project.bloggingapis.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatusCode.valueOf(201));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO,categoryId),HttpStatusCode.valueOf(200));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatusCode.valueOf(200));
    }
}
