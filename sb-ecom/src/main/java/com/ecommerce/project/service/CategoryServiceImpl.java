package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.NoElementException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {

        List<Category> getCategories = categoryRepository.findAll();
        if(getCategories.isEmpty()) {
            throw new NoElementException("No Category created yet");
        }
        return getCategories;
    }

    @Override
    public void createCategory(Category category) {

        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
            throw new APIException("Category with name " + category.getCategoryName() + " already exists!!");
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        categoryRepository.delete(category);

        return "Category with category id " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
}
