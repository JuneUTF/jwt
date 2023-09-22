package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ForgetPassMapper;
import com.example.demo.model.RequetsModel.ForgetPassModel;
import com.example.demo.model.RequetsModel.ForgetPasswordRequets;
import com.example.demo.service.ForgetPassService;

@Service
public class ForgetPassIml implements ForgetPassService{
	@Autowired
	private ForgetPassMapper mapper;
	@Override
	public ForgetPasswordRequets selectEmail(String email) {
		return mapper.selectEmail(email);
	}
	public ForgetPassModel selectKeyNumberByUsername(String email) {
		return mapper.selectKeyNumberByUsername(email);
	}
	public int updateActiveByUsername(String email) {
		return mapper.updateActiveByUsername(email);
	}
}
