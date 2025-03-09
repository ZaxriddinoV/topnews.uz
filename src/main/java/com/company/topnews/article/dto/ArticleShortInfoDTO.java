package com.company.topnews.article.dto;

import com.company.topnews.attach.dto.GetAttachDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleShortInfoDTO {
    private String id;
    private String title;
    private String description;
    private List<GetAttachDTO> images;
    private LocalDateTime published_date;
}
