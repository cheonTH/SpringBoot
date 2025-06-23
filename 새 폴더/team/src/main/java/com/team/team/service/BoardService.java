package com.team.team.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.team.team.dto.BoardDTO;
import com.team.team.model.BoardEntity;
import com.team.team.model.BoardImageEntity;
import com.team.team.repository.BoardImageRepository;
import com.team.team.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository repository;
	private final BoardImageRepository imageRepository;
	
	// 게시물 목록
	public List<BoardDTO> findAll() {
	    List<BoardEntity> posts = repository.findAll();

	    return posts.stream().map(post -> {
	        List<String> imageUrls = imageRepository.findByBoardId(post.getId())
	                .stream()
	                .map(BoardImageEntity::getImageUrl)
	                .collect(Collectors.toList());

	        return BoardDTO.fromEntity(post, imageUrls);
	    }).collect(Collectors.toList());
	}
		
	// 게시물 조회
	public Optional<BoardDTO> getPostByIdWithImages(Long id) {
	    Optional<BoardEntity> postOpt = repository.findById(id);
	    if (postOpt.isEmpty()) return Optional.empty();

	    List<String> imageUrls = imageRepository.findByBoardId(id).stream()
	            .map(BoardImageEntity::getImageUrl)
	            .collect(Collectors.toList());

	    BoardDTO dto = BoardDTO.fromEntity(postOpt.get(), imageUrls);
	    return Optional.of(dto);
	}
		
	// 게시물 생성
	public BoardEntity createPost(BoardDTO dto) {
        BoardEntity entity = BoardDTO.fromDTO(dto);
        return repository.save(entity);
    }

	//좋아요버튼
	public BoardEntity toggleLike(Long id) {
	    BoardEntity board = repository.findById(id)
	        .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

	    if (board.isLiked()) {
	        board.setLiked(false);
	        board.setLikeCount(board.getLikeCount() - 1);
	    } else {
	        board.setLiked(true);
	        board.setLikeCount(board.getLikeCount() + 1);
	    }

	    return repository.save(board);
	}
	
	public BoardEntity createPost2(BoardDTO dto) {
	    // 1. 게시글 엔티티 생성
	    BoardEntity board = BoardEntity.builder()
	            .title(dto.getTitle())
	            .content(dto.getContent())
	            .userId(dto.getUserId())
	            .writingTime(dto.getWritingTime())
	            .likeCount(0)
	            .isLiked(false)
	            .imageUrl(  // ✅ 대표 이미지 저장
	                (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty())
	                ? dto.getImageUrls().get(0)
	                : null
	            )
	            .build();

	    // 2. 저장
	    BoardEntity savedBoard = repository.save(board);

	    // 3. 이미지 엔티티 저장
	    if (dto.getImageUrls() != null) {
	        List<BoardImageEntity> imageEntities = dto.getImageUrls().stream()
	                .map(url -> BoardImageEntity.builder()
	                        .boardId(savedBoard.getId())
	                        .imageUrl(url)
	                        .build())
	                .collect(Collectors.toList());

	        imageRepository.saveAll(imageEntities);
	    }

	    return savedBoard;
	}
	
	public void deletePost(Long id) {
	    repository.deleteById(id);
	    imageRepository.deleteByBoardId(id); // 이미지도 함께 삭제 (직접 쿼리 정의 필요)
	}
	
	// 게시물 수정
	public BoardEntity updatePost(Long id, BoardDTO dto) {
	    BoardEntity existing = repository.findById(id)
	        .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

	    // 게시물 내용 업데이트
	    existing.setTitle(dto.getTitle());
	    existing.setContent(dto.getContent());
	    existing.setUserId(dto.getUserId());
	    existing.setWritingTime(dto.getWritingTime());
	    
	    // 대표 이미지 변경 (옵션)
	    if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {
	        existing.setImageUrl(dto.getImageUrls().get(0));
	    } else {
	        existing.setImageUrl(null);
	    }

	    // 기존 이미지 삭제 후 새 이미지 저장
	    imageRepository.deleteByBoardId(id);
	    if (dto.getImageUrls() != null) {
	        List<BoardImageEntity> imageEntities = dto.getImageUrls().stream()
	                .map(url -> BoardImageEntity.builder()
	                        .boardId(existing.getId())
	                        .imageUrl(url)
	                        .build())
	                .collect(Collectors.toList());
	        imageRepository.saveAll(imageEntities);
	    }

	    return repository.save(existing);
	}
	
}
