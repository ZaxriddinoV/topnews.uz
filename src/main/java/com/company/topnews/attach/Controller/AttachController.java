package com.company.topnews.attach.Controller;



import com.company.topnews.attach.service.AttachService;
import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") List<MultipartFile> file) {
        ApiResponse<?> response = new ApiResponse<>(200,"success",attachService.uploadMultipleFiles(file));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/open/name/{fileName}")
    public ResponseEntity<Resource> open(@PathVariable String fileName) {
        return attachService.open(fileName);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/open")
    public ResponseEntity<?> open(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        ApiResponse<?> response = new ApiResponse<>(200,"success",attachService.allAttaches(page - 1, size));
        return ResponseEntity.ok().body(response);
    }
    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e) {
        ApiResponse<?> response = new ApiResponse<>(200,e.getMessage(),null);
        return ResponseEntity.badRequest().body(response);
    }

}
