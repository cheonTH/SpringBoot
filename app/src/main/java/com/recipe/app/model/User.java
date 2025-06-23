package com.recipe.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;				// 사용자 고유ID
	
	@Column(unique=true, nullable=false)
	private String userId;			// 사용자 계정(로그인할 때 쓸 것)
	
	@Column(unique=true, nullable=false)
	private String username;		// 사용자 닉네임
	
	@Column(unique=true, nullable=false)
	private String email;			// 사용자 이메일
	
	@Column(nullable=false)
	private String password;		// 비밀번호 BCrypt 등으로 해싱
}
