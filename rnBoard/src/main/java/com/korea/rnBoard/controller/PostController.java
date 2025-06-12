package com.korea.rnBoard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.rnBoard.dto.PostDTO;
import com.korea.rnBoard.dto.ResponseDTO;
import com.korea.rnBoard.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {
	private final PostService service;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<PostDTO> dto = service.findAll();
		ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(dto).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getPostById(@PathVariable Long id){
	    List<PostDTO> dto = service.getPostById(id);
	    return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody PostDTO dto) {
		List<PostDTO> list = service.createPost(dto);
		ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	
}
