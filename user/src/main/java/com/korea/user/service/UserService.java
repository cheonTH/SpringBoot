package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이나 @NonNull이 붙은 필드를 생성자의 매개변수로 포함
@Service
public class UserService {
	
	@Autowired
	private final UserRepository repository;
	
	//사용자 생성
	public List<UserDTO> create(UserEntity entity){
		repository.save(entity);
		return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	
	//전체 사용자 조회
	@GetMapping
	public List<UserDTO> getAllUsers(){
		return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	
	//이메일로 사용자 검색
	public UserDTO getUserByEmail(String email){
		UserEntity entity = repository.findByEmail(email);
		return new UserDTO(entity);
	}
	
	
	//ID를 통해 이름과 이메일 수정
	public List<UserDTO> updateUser(UserEntity entity){
		//id를 통해 얻은 기존 데이터
		Optional<UserEntity>  userOptional =  repository.findById(entity.getId());
		
		//사용자가 존재할 경우 업데이트 로직을 실행
		userOptional.ifPresent(userEntity -> {
			//기존 데이터에 새 데이터 세팅
			userEntity.setName(entity.getName());
			userEntity.setEmail(entity.getEmail());
			
			//새 데이터 데이터베이스에 저장
			repository.save(userEntity);
		});
		
		return getAllUsers();
	}
}
