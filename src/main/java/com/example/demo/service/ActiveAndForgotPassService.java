package com.example.demo.service;

import com.example.demo.model.ActiveAndForgotPassModel;

public interface ActiveAndForgotPassService {
	ActiveAndForgotPassModel selectKeyNumberByUsername(String username);
	int updateActiveByUsername(String username);
}
