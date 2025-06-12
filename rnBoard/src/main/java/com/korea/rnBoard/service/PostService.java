package com.korea.rnBoard.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.rnBoard.domain.Post;
import com.korea.rnBoard.dto.PostDTO;
import com.korea.rnBoard.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository repository;
	
	public List<PostDTO> findAll() {
		return repository.findAll().stream().map(PostDTO::fromEntity).collect(Collectors.toList());
	}
	
	public List<PostDTO> getPostById(Long id) {
	    Optional<Post> option = repository.findById(id);
	    
	    PostDTO dto = null;
	    if(option.isPresent()) {
	    	dto = PostDTO.fromEntity(option.get());
	    }
	      
	      
	    return Arrays.asList(dto);
	      
	}

	public List<PostDTO> createPost(PostDTO dto) {
		PostDTO result = PostDTO.fromEntity(repository.save(PostDTO.fromDTO(dto)));
		
		return Arrays.asList(result);
	}
}
