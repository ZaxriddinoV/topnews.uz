package com.company.topnews.article.entity;

import com.company.topnews.article.enums.ArticleStatus;
import com.company.topnews.attach.entity.AttachEntity;
import com.company.topnews.reference.category.entity.CategoryEntity;
import com.company.topnews.reference.section.entity.SectionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "shared_count")
    private Integer sharedCount;
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "moderator_id")
    private Integer moderatorId;
    @Column(name = "publisher_id")
    private Integer publisherId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;
    @Column(name = "read_time")
    private LocalDateTime readTime;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "view_count")
    private Integer viewCount;
    @OneToMany
    private List<AttachEntity> images;

    @ManyToMany
    @JoinTable(name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories;

    @ManyToMany
    @JoinTable(name = "article_section",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<SectionEntity> sectionId;



}
