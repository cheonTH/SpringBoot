package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//클라이언트와 주고받을 때(요청, 응답) DTO에 담아서 주자고 약속
//데이터가 계층간 이동할 때 controller -> service -> repository 이럴 때는 Entity에 담아서 옮김

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
	private int id;
	private String name;
	private String email;
	
	
	public UserDTO(UserEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}
	
	
	public static UserEntity toEntity(UserDTO dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.build();
	}
	
}
