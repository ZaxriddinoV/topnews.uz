/**
 * @author Zaxriddinov Sardor | 20.12.2024
 * @version v1.0.0
 * */

package com.company.topnews.article.controller;

import com.company.topnews.article.dto.CreateArticleDTO;
import com.company.topnews.article.service.ArticleService;
import com.company.topnews.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestPart("dto") @Valid CreateArticleDTO dto,
                                           @RequestPart("file") List<MultipartFile> file){
        ApiResponse<?> response = new ApiResponse<>(200,"created article",articleService.create(dto,file));
        return ResponseEntity.ok(response);
    }
}
