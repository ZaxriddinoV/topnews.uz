package com.company.topnews.reference.section.controller;

import com.company.topnews.reference.section.dto.SectionDTO;
import com.company.topnews.reference.section.service.SectionService;
import com.company.topnews.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/section")
public class CategoryController {
    @Autowired
    private SectionService service;

    @PostMapping("/")
    public ResponseEntity<?> addRegion(@RequestBody @Validated SectionDTO region) {
        ApiResponse<?> response = new ApiResponse<>(200,"success",service.addSection(region));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@RequestBody @Validated SectionDTO region, @PathVariable Integer id) {
        ApiResponse<?> response = new ApiResponse<>(201,"success",service.updateSection(region,id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer id) {
        ApiResponse<?> response = new ApiResponse<>(200,service.deleteSection(id),null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRegions() {
        ApiResponse<?> response = new ApiResponse<>(200,"region all",service.sectionAll());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{lang}")
    public ResponseEntity<?> getRegionByLang(@PathVariable String lang) {
        ApiResponse<?> response = new ApiResponse<>(200,lang + " - all",service.sectionLang(lang));
        return ResponseEntity.ok(response);
    }



}























