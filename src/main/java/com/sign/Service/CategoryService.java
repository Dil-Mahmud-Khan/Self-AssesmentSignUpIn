package com.sign.Service;

import com.sign.model.Category;

import java.util.List;


public interface CategoryService {

    Category addCategory(Category category);
    List<Category> getCategories();

    void deleteCategory(Integer id);

}
