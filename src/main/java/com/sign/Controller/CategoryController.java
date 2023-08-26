package com.sign.Controller;

import com.sign.Service.CategoryService;
import com.sign.Service.CategoryServiceImpl;
import com.sign.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody Category category){
        try{
            categoryService.addCategory(category);
            return ResponseEntity.ok("Category created successfully.");
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Category name already exists.");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id")int id) {
        try {
            this.categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getStudents(){
        return new ResponseEntity<>(
                categoryService.getCategories(),HttpStatus.FOUND
        );
    }

}
