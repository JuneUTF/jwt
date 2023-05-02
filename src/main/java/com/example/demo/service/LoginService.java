package com.example.demo.service;

import com.example.demo.model.LoginModel;

public interface LoginService {
	LoginModel selectLoginByUsername(String username);
}
