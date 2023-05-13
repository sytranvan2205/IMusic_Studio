package com.imusicstudio.service.serviceImpl;

import com.imusicstudio.entities.Category;
import com.imusicstudio.repository.CategoryRepository;
import com.imusicstudio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }//findAll

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }//add or update (tuy vao pri-key)

    public void removeCategoryById(int id){
        categoryRepository.deleteById(String.valueOf(id));
    }//delete truyen vao pri-key

    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(String.valueOf(id));
    }//search theo id

}
