package com.example.demo.model;

import lombok.Data;

@Data
public class LoginModel {
	private int id;
	private String username;
	private String password;
	private String roles;
	private String status;

	// getters và setters
}
