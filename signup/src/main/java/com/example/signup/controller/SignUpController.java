package com.example.signup.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.signup.dto.SignUpDTO;
import com.example.signup.service.SignUpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
	private final SignUpService service;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<SignUpDTO> dto = service.findAll();
		
		
	}
}
