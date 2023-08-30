package com.project2morrow.lapp.Service;

import com.project2morrow.lapp.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CategoryService {

    Category addCategory(Category category);
    List<Category> getCategories();

    ResponseEntity<String> deleteCategory(Integer id);


}
