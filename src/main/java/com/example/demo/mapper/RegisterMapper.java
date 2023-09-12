package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.RequetsModel.RegisterModel;

@Mapper
public interface RegisterMapper {
	int registerUsername(RegisterModel registerModel);
	int registerAdmin(RegisterModel registerModel);
	RegisterModel checkUserAndEmail(RegisterModel registerModel);
}
