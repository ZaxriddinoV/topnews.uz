package com.company.topnews.likes.commentLike.entity;

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
@Table(name = "comment_like")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "emotion")
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

}
