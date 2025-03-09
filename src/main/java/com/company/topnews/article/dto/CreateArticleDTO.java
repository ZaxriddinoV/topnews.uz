package com.company.topnews.article.dto;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateArticleDTO {
    @Max(value = 225,message = "max 500")
    private String title;
    @Max(value = 500,message = "max 500")
    private String description;
    @Max(value = 500,message = "max 500")
    private String content;
    private Integer regionId;
    private List<Integer> categoryList;
    private List<Integer> sectionList;

}
