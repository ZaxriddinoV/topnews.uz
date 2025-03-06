package com.company.topnews.usernameHistory.controller;



import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.usernameHistory.entiy.EmailHistoryEntity;
import com.company.topnews.usernameHistory.service.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/email-history")
public class EmailHistoryController {
    @Autowired
    EmailHistoryService emailHistoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmailHistoryService(@RequestParam String email) {
        List<EmailHistoryEntity> entity =  emailHistoryService.getByEmail(email);
        return ResponseEntity.ok(entity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getEmailHistoryService(@RequestParam LocalDate date) {
        return ResponseEntity.ok(emailHistoryService.getAllGiven(date));
    }

    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
