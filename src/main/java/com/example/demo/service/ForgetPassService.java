package com.example.demo.service;

import com.example.demo.model.RequetsModel.ForgetPassModel;
import com.example.demo.model.RequetsModel.ForgetPasswordRequets;

public interface ForgetPassService {
	ForgetPasswordRequets selectEmail(String email);
	ForgetPassModel selectKeyNumberByUsername(String username);
	int updateActiveByUsername(String username);
}
