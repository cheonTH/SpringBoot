package com.example.signup.dto;

import com.example.signup.model.SignUpEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
	private String id;
	private String password;
	private String address;
	private String email;
	
	public static SignUpDTO fromEntity(SignUpEntity entity) {
		return SignUpDTO.builder()
				.id(entity.getId())
				.password(entity.getPassword())
				.address(entity.getAddress())
				.email(entity.getEmail())
				.build();
	}
	
	public static SignUpEntity fromDTO(SignUpDTO dto) {
		return SignUpEntity.builder()
				.id(dto.getId())
				.password(dto.getPassword())
				.address(dto.getAddress())
				.email(dto.getEmail())
				.build();
	}
}
