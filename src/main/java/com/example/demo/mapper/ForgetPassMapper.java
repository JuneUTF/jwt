package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.RequetsModel.ForgetPassModel;

@Mapper
public interface ForgetPassMapper {
	ForgetPassModel selectKeyNumberByUsername(String username);
	int updateActiveByUsername(String username);

}
