package com.husony.device_service.controller;


import com.husony.device_service.pojo.Category;
import com.husony.device_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategory(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Category>>(this.categoryService.getCategory(params), HttpStatus.OK);
    }

    @PostMapping("/category/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody Category c){
        System.out.println(c);
        this.categoryService.addOrUpdate(c);
    }

    @DeleteMapping("/category/delete/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "categoryId") long id) {
        this.categoryService.deleteCate(id);
    }
}
