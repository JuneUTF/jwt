package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.RegisterMapper;
import com.example.demo.model.RequetsModel.RegisterModel;
import com.example.demo.service.RegisterService;
@Service
public class RegisterImpl implements RegisterService {
	@Autowired
	private RegisterMapper mapper;
	public int registerUsername(RegisterModel registerModel) {
		return mapper.registerUsername(registerModel);
	}
	public int registerAdmin(RegisterModel registerModel) {
		return mapper.registerAdmin(registerModel);
	}
	@Override
	public RegisterModel checkUserAndEmail(RegisterModel registerModel) {
		return mapper.checkUserAndEmail(registerModel);
	}
	
}
