package com.company.topnews.article.service;

import com.company.topnews.article.dto.ArticleShortInfoDTO;
import com.company.topnews.article.dto.CreateArticleDTO;
import com.company.topnews.article.entity.ArticleEntity;
import com.company.topnews.article.enums.ArticleStatus;
import com.company.topnews.article.repository.ArticleRepository;
import com.company.topnews.attach.dto.GetAttachDTO;
import com.company.topnews.attach.service.AttachService;
import com.company.topnews.reference.category.service.CategoryService;
import com.company.topnews.reference.region.service.RegionService;
import com.company.topnews.reference.section.service.SectionService;
import com.company.topnews.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SectionService sectionService;


    public ArticleShortInfoDTO create(CreateArticleDTO dto, List<MultipartFile> files) {
        if (files.isEmpty()) {
            ArticleEntity articleEntity = entitySave(dto);
            articleRepository.save(articleEntity);
            return result(articleEntity);
        } else {
            ArticleEntity articleEntity = entitySave(dto);
            articleEntity.setImages(attachService.articleImages(files));
            articleRepository.save(articleEntity);
            return result(articleEntity);
        }
    }

    private ArticleEntity entitySave(CreateArticleDTO dto) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setDescription(dto.getDescription());
        articleEntity.setCategories(categoryService.checkCategory(dto.getCategoryList()));
        articleEntity.setCreatedDate(LocalDateTime.now());
        articleEntity.setContent(dto.getContent());
        articleEntity.setModeratorId(SpringSecurityUtil.getCurrentUserId());
        articleEntity.setRegionId(regionService.checkRegion(dto.getRegionId()));
        articleEntity.setSectionId(sectionService.checkSection(dto.getSectionList()));
        articleEntity.setStatus(ArticleStatus.NotPublished);
        articleEntity.setVisible(Boolean.TRUE);
        return articleEntity;
    }

    private ArticleShortInfoDTO result(ArticleEntity dto) {
        List<GetAttachDTO> images = new ArrayList<>();
        ArticleShortInfoDTO article = new ArticleShortInfoDTO();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setPublished_date(dto.getPublishedDate());
        article.setImages(attachService.getUrls(dto.getImages()));
        return article;
    }
}
