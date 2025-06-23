package com.recipe.app.controller;

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

import com.recipe.app.model.Post;
import com.recipe.app.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping
	public List<Post> getAllPosts() {
		return boardService.getAllPosts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		return boardService.getPostById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		return boardService.createPost(post);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
		try {
			return ResponseEntity.ok(boardService.updatePost(id, post));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable Long id) {
		boardService.deletePost(id);
		return ResponseEntity.noContent().build();
	}
}
