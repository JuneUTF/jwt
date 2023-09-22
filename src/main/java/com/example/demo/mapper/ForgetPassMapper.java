package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.RequetsModel.ForgetPassModel;
import com.example.demo.model.RequetsModel.ForgetPasswordRequets;

@Mapper
public interface ForgetPassMapper {
	ForgetPasswordRequets selectEmail(String email);
	ForgetPassModel selectKeyNumberByUsername(String username);
	int updateActiveByUsername(String username);

}
