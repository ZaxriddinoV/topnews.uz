package com.company.topnews.reference.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer orderNumber;
    @NotBlank(message = "uz not found")
    private String nameUz;
    @NotBlank(message = "ru not found")
    private String nameRu;
    @NotBlank(message = "en not found")
    private String nameEn;
    private String key;
}
