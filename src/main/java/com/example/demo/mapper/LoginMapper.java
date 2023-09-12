package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.RequetsModel.LoginModel;

@Mapper
public interface LoginMapper {
	LoginModel selectLoginByUsername(String email);
}
