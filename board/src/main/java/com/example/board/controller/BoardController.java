package com.example.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/add")
	public ResponseEntity<?> createPost(@RequestBody BoardDTO dto) {
		List<BoardDTO> list = service.createPost(dto);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") long id){
		List<BoardDTO> list = service.deletePost(id);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
}
