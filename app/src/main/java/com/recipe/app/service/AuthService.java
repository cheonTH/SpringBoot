package com.recipe.app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recipe.app.dto.LoginRequest;
import com.recipe.app.dto.RegisterRequest;
import com.recipe.app.model.User;
import com.recipe.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// 가입, 로그인 인증 관련
@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 가입 인증
	public User registerUser(RegisterRequest request) {
		if(userRepository.findByUserId(request.getUserId()).isPresent()) {
			throw new RuntimeException("이미 존재하는 아이디입니다");
		}
		if(userRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new RuntimeException("이미 존재하는 닉네임입니다.");
		}
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("이미 존재하는 이메일입니다.");
		}
		
		User user = new User();
		user.setUserId(request.getUserId());	// 사용자 ID
		user.setUsername(request.getUsername());// 사용자 닉네임
		user.setEmail(request.getEmail());		// 사용자 이메일
		user.setPassword(passwordEncoder.encode(request.getPassword()));  // 사용자 패스워드
		
		return userRepository.save(user);
	}
	
	// 로그인 인증
	public User authenticateUser(LoginRequest request) {
		User user = userRepository.findByUserId(request.getUserId())
				.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("올바른 비밀번호를 입력해주세요");
		}
		
		return user;
	}
}
