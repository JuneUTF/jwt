package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.LoginMapper;
import com.example.demo.model.LoginModel;
import com.example.demo.service.LoginService;

@Service
public class LoginImpl implements LoginService {

	@Autowired
	LoginMapper mapper;
	
	@Override
	  public LoginModel selectLoginByUsername(String username){
        return mapper.selectLoginByUsername(username);
    }
	
}
