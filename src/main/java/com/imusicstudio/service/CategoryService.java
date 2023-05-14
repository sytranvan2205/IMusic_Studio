package com.imusicstudio.service;

import java.util.List;
import java.util.Optional;

import com.imusicstudio.entities.Category;




public interface CategoryService {
    List<Category> getAllCategory();

    public void updateCategory(Category category);

    public void removeCategoryById(int id);

    public Optional<Category> getCategoryById(int id);

    Category getCategoryById(Long id);
    void saveCategory(Category category);

    void deleteCategory(Long id);
}
