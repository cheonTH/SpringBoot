package com.team.team.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.team.dto.BoardDTO;
import com.team.team.dto.ResponseDTO;
import com.team.team.model.BoardEntity;
import com.team.team.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
	private final BoardService service;
	
	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<BoardDTO> dto = service.findAll();
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dto).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getPostById(@PathVariable Long id) {
        return service.getPostByIdWithImages(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	 @PostMapping
	    public ResponseEntity<BoardEntity> createPost(@RequestBody BoardDTO dto) {
		 System.out.println("받은 이미지 개수: " + (dto.getImageUrls() != null ? dto.getImageUrls().size() : 0));
		 if (dto.getImageUrls() != null) {
		     dto.getImageUrls().forEach(img -> System.out.println("이미지 데이터 일부: " + img.substring(0, 30)));
		 }
	        BoardEntity saved = service.createPost2(dto);
	        return ResponseEntity.ok(saved);
	    }
	
	@PostMapping("/{id}/like")
	public ResponseEntity<BoardEntity> toggleLike(@PathVariable Long id) {
	    BoardEntity updatedPost = service.toggleLike(id);
	    return ResponseEntity.ok(updatedPost);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BoardEntity> updatePost(
	        @PathVariable Long id,
	        @RequestBody BoardDTO dto) {
	    BoardEntity updated = service.updatePost(id, dto);
	    return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {
	    try {
	        service.deletePost(id);
	        return ResponseEntity.ok().body("삭제 성공");
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("삭제 실패: " + e.getMessage());
	    }
	}
}
