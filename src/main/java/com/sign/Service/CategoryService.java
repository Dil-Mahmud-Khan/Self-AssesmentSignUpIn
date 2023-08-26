package com.sign.Service;

import com.sign.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    Category addCategory(Category category);
    List<Category> getCategories();

    void deleteCategory(Integer id);

}
