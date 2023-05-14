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

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.getById(id);
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}// findAll

	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}// add or update (tuy vao pri-key)

	public void removeCategoryById(int id) {
		categoryRepository.deleteById(Long.valueOf(id));
	}// delete truyen vao pri-key

	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(Long.valueOf(id));
	}// search theo id

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();

	}

	@Override
	public Category save(Category category) {
		Category categorySave = new Category(category.getCategoryName());
		return categoryRepository.save(categorySave);
	}
	
	@Override
	public Category getById(Long id) {
		return categoryRepository.getById(id);
	}

	@Override
	public Category update(Category category) {
		Category categoryUpdate = new Category();
		categoryUpdate.setCategoryName(category.getCategoryName());
//        categoryUpdate.setIs_activated(category.isIs_activated());
//        categoryUpdate.setIs_deleted(category.isIs_deleted());
		return categoryRepository.save(categoryUpdate);
	}
	
	@Override
	public void deleteById(Long id) {
		Category category = categoryRepository.getById(id);
//        category.setIs_deleted(true);
//        category.setIs_activated(false);
		categoryRepository.save(category);

	}

	@Override
	public void enableById(Long id) {
		Category category = categoryRepository.getById(id);
//category.setIs_activated(true);
//category.setIs_deleted(false);
		categoryRepository.save(category);
	}
}
