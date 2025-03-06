package com.company.topnews.reference.region.controller;

import com.company.topnews.reference.region.dto.RegionDTO;
import com.company.topnews.reference.region.service.RegionService;
import com.company.topnews.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    @Autowired
    private RegionService service;

    @PostMapping("/")
    public ResponseEntity<?> addRegion(@RequestBody RegionDTO region) {
        ApiResponse<?> response = new ApiResponse<>(200,"success",service.addRegion(region));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@RequestBody RegionDTO region, @PathVariable Integer id) {
        ApiResponse<?> response = new ApiResponse<>(201,"success",service.updateRegion(region,id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer id) {
        ApiResponse<?> response = new ApiResponse<>(200,service.deleteRegion(id),null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRegions() {
        ApiResponse<?> response = new ApiResponse<>(200,"region all",service.regionAll());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{lang}")
    public ResponseEntity<?> getRegionByLang(@PathVariable String lang) {
        ApiResponse<?> response = new ApiResponse<>(200,lang + " - all",service.regionLang(lang));
        return ResponseEntity.ok(response);
    }



}























