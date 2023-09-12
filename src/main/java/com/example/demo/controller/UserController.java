package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseModel.ResponseMessager;

@RestController
@CrossOrigin
@RequestMapping("/v1/user")
public class UserController {

//	ユーザー名の情報が取得
	@GetMapping("/mypage")
	public ResponseEntity<?> myPage(ResponseMessager responseMessager) {

		return ResponseEntity.ok("myPage");
	}

}
