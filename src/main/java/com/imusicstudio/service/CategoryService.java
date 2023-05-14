package com.imusicstudio.service;

import java.util.List;
import java.util.Optional;

import com.imusicstudio.entities.Category;




public interface CategoryService {
    List<Category> getAllCategory();
    
    Category getCategoryById(Long categoryId);
//    List<Category> findAll();
//    Category save(Category category);
//    Category getById(Long id);
//    Category update(Category category);
//    void deleteById(Long id);
//    void enableById(Long id);

    public void updateCategory(Category category);

    public void removeCategoryById(int id);

    public Optional<Category> getCategoryById(int id);

    void saveCategory(Category category);

    void deleteCategory(Long id);

	List<Category> findAll();

	Category save(Category category);

	Category getById(Long id);

	Category update(Category category);

	void deleteById(Long id);

	void enableById(Long id);
}
