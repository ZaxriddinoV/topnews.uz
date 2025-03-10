package com.company.topnews.reference.category.controller;

import com.company.topnews.reference.category.dto.CategoryDTO;
import com.company.topnews.reference.category.service.CategoryService;
import com.company.topnews.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/")
    public ResponseEntity<?> addRegion(@RequestBody CategoryDTO region) {
        ApiResponse<?> response = new ApiResponse<>(200,"success",service.addCategory(region));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@RequestBody CategoryDTO region, @PathVariable Integer id) {
        ApiResponse<?> response = new ApiResponse<>(201,"success",service.updateCategory(region,id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer id) {
        ApiResponse<?> response = new ApiResponse<>(200,service.deleteCategory(id),null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRegions() {
        ApiResponse<?> response = new ApiResponse<>(200,"region all",service.categoryAll());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/listAll")
    public ResponseEntity<?> getRegionByLang(@RequestHeader(value = "Accept-Language",defaultValue = "ru") String lang) {
        ApiResponse<?> response = new ApiResponse<>(200,lang + " - all",service.categoryLang(lang));
        return ResponseEntity.ok(response);
    }


}























