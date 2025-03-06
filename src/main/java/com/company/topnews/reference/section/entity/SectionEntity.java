package com.company.topnews.reference.section.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_number", unique = true)
    private Integer orderNumber;
    @Column(name = "name_uz", unique = true)
    private String nameUz;
    @Column(name = "name_ru", unique = true)
    private String nameRu;
    @Column(name = "name_en", unique = true)
    private String nameEn;
    @Column(name = "key")
    private String key;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "image_id")
    private String imageId;

}
