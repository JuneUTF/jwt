package com.example.demo.service;

import com.example.demo.model.RequetsModel.ForgetPassModel;

public interface ForgetPassService {
	ForgetPassModel selectKeyNumberByUsername(String username);
	int updateActiveByUsername(String username);
}
