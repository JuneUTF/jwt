package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.reponseModel.ResponseMessager;

@RestController
@CrossOrigin
@RequestMapping("/v1/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
//	一般ユーザー名の関係API
//	Show list activeuser　　一般ユーザー名が表示
	@GetMapping("/showuser")
	public ResponseEntity<?> showUser(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("showUser");
}
	
//	Show list deleteuser　一般削除したユーザー名が表示
	@GetMapping("/showdeleteuser")
	public ResponseEntity<?> showDeleteUser(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("showDeleteUser");
}
	
//	user delete　ユーザー名が削除
	@DeleteMapping("/deleteuser")
	public ResponseEntity<?> deleteUser(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("deleteUser");
}
	
//	user restore ユーザー名が復旧
	@PutMapping("/restoreuser")
	public ResponseEntity<?> restoreUser(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("restoreUser");
}
//	user restore ユーザー名が編集
	@PutMapping("/edituser")
	public ResponseEntity<?> editUser(ResponseMessager responseMessager) {
		  System.out.println("ok");
        return ResponseEntity.ok("editUser");
}

	
//	これから管理者の関係API
//	Show list admin　管理者ユーザー名が表示
	@GetMapping("/showadmin")
	public ResponseEntity<?> showAdmin(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("showAdmin");
}
	
//	Show list deleteuser　管理者削除したユーザー名が表示
	@GetMapping("/showdeleteadmin")
	public ResponseEntity<?> showDeleteAdmin(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("showDeleteAdmin");
}
//	set admin　ユーザー名が管理者として設定
	@PutMapping("/setadmin")
	public ResponseEntity<?> setAdmin(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("setAdmin");
}
//	remove admin　ユーザー名が管理者として削除
	@DeleteMapping("/removeadmin")
	public ResponseEntity<?> removeAdmin(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("removeAdmin");
}
//	新規登録
	@PostMapping("/adminregister")
	public ResponseEntity<?> adminRegister(ResponseMessager responseMessager) {
		  
        return ResponseEntity.ok("adminRegister");
}
}
