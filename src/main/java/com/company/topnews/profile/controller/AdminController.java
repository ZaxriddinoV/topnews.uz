package com.company.topnews.profile.controller;

import com.company.topnews.profile.dto.AdminCreateDTO;
import com.company.topnews.profile.service.AdminService;
import com.company.topnews.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public ResponseEntity<?> addAdmin(@RequestBody AdminCreateDTO dto) {
        ApiResponse<?> response = new ApiResponse<>(200,"created",adminService.adminCreate(dto));
        return ResponseEntity.ok(response);
    }

}