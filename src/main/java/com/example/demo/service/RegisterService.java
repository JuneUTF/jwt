package com.example.demo.service;

import com.example.demo.model.RequetsModel.RegisterModel;

public interface RegisterService {
	int registerUsername(RegisterModel registerModel);
	int registerAdmin(RegisterModel registerModel);
	RegisterModel checkUserAndEmail(RegisterModel registerModel);
}
