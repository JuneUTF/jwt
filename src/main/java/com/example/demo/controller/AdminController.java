package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseModel.ResponseMessager;
import com.example.demo.requetsUrl.AdminURL;

@RestController
@CrossOrigin
@RequestMapping(AdminURL.keyRole)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
//	一般ユーザー名の関係API
//	Show list activeuser　　一般ユーザー名が表示
	@GetMapping(AdminURL.showuserURL)
	public ResponseEntity<?> showUser(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("showUser");
}

}
