package com.imusicstudio.controller.admin;
import com.imusicstudio.entities.Category;
import com.imusicstudio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/category-form";
        }

        try {
            categoryService.saveCategory(category);
            redirectAttributes.addFlashAttribute("success", "Category added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("failed", "Failed to add category due to duplicate name");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", "Error: " + e.getMessage());
        }

        return "redirect:/admintest/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            //lỗi không tìm thấy
            return "admin/category-not-found";
        }
        model.addAttribute("category", category);
        return "admin/category-edit-form";
    }








    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @Valid @ModelAttribute("category") Category updatedCategory, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/category-edit-form";
        }

        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            //lỗi không tìm thấy
            return "admin/category-not-found";
        }

        category.setCategoryName(updatedCategory.getCategoryName());

        try {
            categoryService.updateCategory(category);
            redirectAttributes.addFlashAttribute("success", "Category updated successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("failed", "Failed to update category due to duplicate name");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", "Error: " + e.getMessage());
        }

        return "redirect:/admintest/category";
    }

//    @PostMapping("/edit")
//    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            return "admin/category-edit-form";
//        }
//
//        try {
//            categoryService.updateCategory(category);
//            redirectAttributes.addFlashAttribute("success", "Category updated successfully");
//        } catch (DataIntegrityViolationException e) {
//            redirectAttributes.addFlashAttribute("failed", "Failed to update category due to duplicate name");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("failed", "Error: " + e.getMessage());
//        }
//
//        return "redirect:/admintest/category";
//    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Xóa danh mục
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", "Failed to delete category");
        }

        return "redirect:/admintest/category";
    }





//    @PostMapping("/delete/{id}")
//    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        // Xóa danh mục
//        try {
//            categoryService.deleteCategory(id);
//            redirectAttributes.addFlashAttribute("success", "Category deleted successfully");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("failed", "Failed to delete category");
//        }
//
//        return "redirect:/admin/category";
//    }

}
