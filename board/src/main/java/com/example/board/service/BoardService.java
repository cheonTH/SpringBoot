package com.example.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDTO;
import com.example.board.model.BoardEntity;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	//전체 조회
	private final BoardRepository repository;
	
	public List<BoardDTO> findAll() {
		return repository.findAll().stream().map(BoardDTO::fromEntity).collect(Collectors.toList());
	}
	
	//한건 조회
	public BoardDTO getPostById(Long id) {
	    //id에 해당하는 게시물이 존재하는지 찾는다.
	    Optional<BoardEntity> option = repository.findById(id);
	      
	    BoardEntity entity = option.get();
	      
	    return BoardDTO.fromEntity(entity);
	      
	}
	
	//추가
	public List<BoardDTO> createPost(BoardDTO dto) {
		BoardEntity entity = BoardDTO.fromDTO(dto);
		repository.save(entity);
		return findAll();
	}
	
	//수정
	public List<BoardDTO> updateBoard(BoardEntity entity) {
		Optional<BoardEntity> boards = repository.findById(entity.getId());
		
		boards.ifPresent(boardEntity -> {
			//기존 데이터에 새 데이터 세팅
			boardEntity.setId(entity.getId());
			boardEntity.setTitle(entity.getTitle());
			boardEntity.setAuthor(entity.getAuthor());
			boardEntity.setWritingTime(entity.getWritingTime());
			boardEntity.setContent(entity.getContent());
			
			//새 데이터 데이터베이스에 저장
			repository.save(boardEntity);
		});
		
		return findAll();
		
		
	}

	//삭제
	public List<BoardDTO> deletePost(long id) {
		repository.deleteById(id);
		
		return findAll();
	}

	
}
