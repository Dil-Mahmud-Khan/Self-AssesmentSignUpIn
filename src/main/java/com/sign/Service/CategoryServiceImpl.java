package com.sign.Service;

import com.sign.Repository.CategoryRepository;
import com.sign.exception.CategoryDeletionException;
import com.sign.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new CategoryDeletionException("Error while deleting category: " + e.getMessage());
        }
    }

}
