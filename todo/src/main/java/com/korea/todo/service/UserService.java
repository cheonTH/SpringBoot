package com.korea.todo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.korea.todo.model.UserEntity;
import com.korea.todo.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	//repository 생성자 주입
	private final UserRepository repository;
	
	//유저의 추가
	//회원가입 화면에서 넘어오는 정보
	public UserEntity create(UserEntity entity) {
		//주어진 UserEntity가 null이거나 또는 username이 null인경우 예외를 발생시킴
		if(entity == null || entity.getUsername() == null) {
			throw new RuntimeException("Invalid argument");
		}
		
		//entity에서 username을 가져옴
		final String username = entity.getUsername();
		
		//주어진 username이 이미 존재하는 경우 경고 로그를 남기고 예외를 던짐
		if(repository.existsByUsername(username)) {
			log.warn("Username already exist {}", username);
			throw new RuntimeException("Username already exists");
		}
		
		//username이 중복되지 않았다면 entity객체를 데이터베이스에 추가
		return repository.save(entity);
	}
	
	//주어진 username과 password를 이용해서 entity를 조회함
	public UserEntity getByCredential(String username, String password, final PasswordEncoder encoder) {
		final UserEntity originalUser = repository.findByUsername(username);
		if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
		
		return null;
	}
}
