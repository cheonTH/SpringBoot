package com.korea.rnBoard.dto;

import java.time.LocalDateTime;

import com.korea.rnBoard.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
	private Long id;
	private String title;
	private String author;
	private String description;
	private String time;
	private int views;
	
	public static PostDTO fromEntity(Post entity) {
		return PostDTO.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.author(entity.getAuthor())
				.description(entity.getDescription())
				.time(entity.getTime())
				.views(entity.getViews())
				.build();
	}
	
	public static Post fromDTO(PostDTO dto) {
		return Post.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor())
				.description(dto.getDescription())
				.time(dto.getTime())
				.views(dto.getViews())
				.build();
	}
}
