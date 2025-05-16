package com.example.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.ResponseDTO;
import com.example.board.model.BoardEntity;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository repository;
	
	public List<BoardDTO> findAll() {
		return repository.findAll().stream().map(BoardDTO::fromEntity).collect(Collectors.toList());
	}

	public List<BoardDTO> createPost(BoardDTO dto) {
		BoardEntity entity = BoardDTO.fromDTO(dto);
		repository.save(entity);
		return findAll();
	}

	public List<BoardDTO> deletePost(long id) {
		repository.deleteById(id);
		
		return findAll();
	}

	
	
	
}
