package com.imusicstudio.service;

import com.imusicstudio.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category getById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enableById(Long id);
}
