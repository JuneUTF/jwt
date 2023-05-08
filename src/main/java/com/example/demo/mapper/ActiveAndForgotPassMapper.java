package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.ActiveAndForgotPassModel;

@Mapper
public interface ActiveAndForgotPassMapper {
	ActiveAndForgotPassModel selectKeyNumberByUsername(String username);
	int updateActiveByUsername(String username);

}
