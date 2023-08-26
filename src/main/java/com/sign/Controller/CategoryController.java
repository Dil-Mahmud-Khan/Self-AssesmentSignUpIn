package com.sign.Controller;

import com.sign.Service.CategoryService;
import com.sign.exception.CategoryDeletionException;
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
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable("id")int id) {
//        try {
//            this.categoryService.deleteCategory(id);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (Exception e) {
//            throw new CategoryDeletionException("Error while deleting category: " + e.getMessage());
//        }
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        try {
            this.categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (CategoryDeletionException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            // Handle other exceptions
            ex.printStackTrace();
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
