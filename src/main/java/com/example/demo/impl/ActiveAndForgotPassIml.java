package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ActiveAndForgotPassMapper;
import com.example.demo.model.ActiveAndForgotPassModel;
import com.example.demo.service.ActiveAndForgotPassService;

@Service
public class ActiveAndForgotPassIml implements ActiveAndForgotPassService{
	@Autowired
	private ActiveAndForgotPassMapper mapper;
	@Override
	public ActiveAndForgotPassModel selectKeyNumberByUsername(String username) {
		return mapper.selectKeyNumberByUsername(username);
	}
	public int updateActiveByUsername(String username) {
		return mapper.updateActiveByUsername(username);
	}
}
