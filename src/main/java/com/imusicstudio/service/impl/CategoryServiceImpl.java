package com.imusicstudio.service.impl;

import com.imusicstudio.entities.Category;
import com.imusicstudio.repository.CategoryRepository;
import com.imusicstudio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Override
    public List<Category> findAll() {
        return repository.findAll();

    }

    @Override
    public Category save(Category category) {
        Category categorySave=new Category(category.getCategoryName());
        return repository.save(categorySave);
    }

    @Override
    public Category getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate=new Category();
        categoryUpdate.setCategoryName(category.getCategoryName());
//        categoryUpdate.setIs_activated(category.isIs_activated());
//        categoryUpdate.setIs_deleted(category.isIs_deleted());
        return repository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category=repository.getById(id);
//        category.setIs_deleted(true);
//        category.setIs_activated(false);
        repository.save(category);

    }

    @Override
    public void enableById(Long id) {
Category category=repository.getById(id);
//category.setIs_activated(true);
//category.setIs_deleted(false);
repository.save(category);
    }
}
