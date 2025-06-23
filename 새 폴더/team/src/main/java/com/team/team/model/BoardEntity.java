package com.team.team.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;         // 게시글 제목
    private String content;       // 본문
    private String userId;        // 작성자 ID 또는 닉네임

    private String writingTime;

    private int likeCount = 0;
    
    @JsonProperty("isLiked")
    private boolean isLiked = false;
    
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
}