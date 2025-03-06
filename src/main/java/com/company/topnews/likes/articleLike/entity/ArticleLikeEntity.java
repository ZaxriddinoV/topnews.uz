package com.company.topnews.likes.articleLike.entity;

import com.company.topnews.likes.enums.Emotion;
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
@Table(name = "article_like")
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "article_id")
    private String articleId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "emotion")
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

}
