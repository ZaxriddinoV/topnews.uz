package com.company.topnews.article.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateArticleDTO {
    //    @Max(value = 225,message = "max 500")
    @NotBlank(message = "title not blank")
    private String title;
    //    @Max(value = 500,message = "max 500")
    @NotBlank(message = "description not blank")
    private String description;
    //    @Max(value = 500,message = "max 500")
    @NotBlank(message = "content not blank")
    private String content;
    private Integer regionId;
    private List<Integer> categoryList;
    private List<Integer> sectionList;

}
