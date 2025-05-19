package com.example.board.controller;

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

import com.example.board.dto.BoardDTO;
import com.example.board.dto.ResponseDTO;
import com.example.board.model.BoardEntity;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
	
	private final BoardService service;
	
	//전체 조회
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<BoardDTO> dto = service.findAll();
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dto).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	//id를 통한 게시글 한 건 조회하기("/{id}") (@PathVariable 사용하기)
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById(@PathVariable long id){
	    BoardDTO dto = service.getPostById(id);
//	    ResponseDTO<BoardDTO> response = ResponseDTO
//	                                 .<BoardDTO>builder()
//	                                 .data(dto).build();
	    return ResponseEntity.ok().body(dto);
	}
	
	//추가
	@PostMapping("/add")
	public ResponseEntity<?> createPost(@RequestBody BoardDTO dto) {
		List<BoardDTO> list = service.createPost(dto);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	//수정
	@PutMapping("/put/{id}")
	public ResponseEntity<?> putBoard(@PathVariable long id,  @RequestBody BoardDTO dto){
		BoardEntity entity = dto.fromDTO(dto);
		entity.setId(id);
		List<BoardDTO> boards = service.updateBoard(entity);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	//삭제
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePost(@PathVariable long id){
		List<BoardDTO> list = service.deletePost(id);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
}
