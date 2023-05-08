package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.reponseModel.ResponseMessager;

@RestController
@CrossOrigin
@RequestMapping("/v1/api/user")
public class UserController {

//	ユーザー名の情報が取得
	@GetMapping("/mypage")
	public ResponseEntity<?> myPage(ResponseMessager responseMessager) {

		return ResponseEntity.ok("myPage");
	}

//	ユーザー名の情報が編集
	@PostMapping("/edit")
	public ResponseEntity<?> edit(ResponseMessager responseMessager) {

		return ResponseEntity.ok("edit");
	}

//	ユーザー名の情報が削除
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(ResponseMessager responseMessager) {

		return ResponseEntity.ok("delete");
	}

//	ユーザー名のパスワードを変更
	@PostMapping("/changepassword")
	public ResponseEntity<?> changePassword(ResponseMessager responseMessager) {

		return ResponseEntity.ok("changePassword");
	}

}
