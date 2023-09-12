package com.example.demo.service;

import com.example.demo.model.RequetsModel.LoginModel;

public interface LoginService {
	LoginModel selectLoginByUsername(String email);
}
