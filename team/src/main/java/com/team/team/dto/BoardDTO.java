package com.team.team.dto;

import java.util.List;

import com.team.team.model.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	private Long id;
    private String title;         // 게시글 제목
    private String content;       // 본문
    private String userId;        // 작성자 ID 또는 닉네임
    private List<String> imageUrls;      // 대표 이미지 URL (단일 이미지)
    private String writingTime;
    private int likeCount;
    private boolean isLiked;
    private String difficulty;
    private int cookingTime;
    private String category;
    
    public static BoardDTO fromEntity(BoardEntity entity, List<String> imageUrls) {
        return BoardDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .userId(entity.getUserId())
                .content(entity.getContent())
                .writingTime(entity.getWritingTime())
                .likeCount(entity.getLikeCount())
                .isLiked(entity.isLiked())
                .imageUrls(imageUrls)
                .difficulty(entity.getDifficulty())
                .cookingTime(entity.getCookingTime())
                .category(entity.getCategory())
                .build();
    }
	
	public static BoardEntity fromDTO(BoardDTO dto) {
		return BoardEntity.builder()
	            .id(dto.getId())
	            .title(dto.getTitle())
	            .userId(dto.getUserId())
	            .content(dto.getContent())
	            .writingTime(dto.getWritingTime())
	            .likeCount(dto.getLikeCount())
	            .isLiked(dto.isLiked())
	            .difficulty(dto.getDifficulty())
	            .cookingTime(dto.getCookingTime())
	            .category(dto.getCategory())
	            .build();
	}
}
