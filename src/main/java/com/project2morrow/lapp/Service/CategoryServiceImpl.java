package com.project2morrow.lapp.Service;

import com.project2morrow.lapp.model.Category;
import com.project2morrow.lapp.Repository.CategoryRepository;
import com.project2morrow.lapp.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
            return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public ResponseEntity deleteCategory(Integer id) {
        try {
            categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));
            System.out.println("In try block");
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully.");
        } catch (Exception e) {
            System.out.println("In catch block");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }

    }
}
