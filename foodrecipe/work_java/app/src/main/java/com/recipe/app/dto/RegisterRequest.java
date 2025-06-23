package com.recipe.app.dto;

import lombok.Data;

@Data
public class RegisterRequest {
	private String userId;
	private String username;
	private String email;
	private String password;
}
