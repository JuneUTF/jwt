package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.LoginModel;

@Mapper
public interface LoginMapper {
	LoginModel selectLoginByUsername(String username);
}
