package com.example.demo.model.RequetsModel;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
/**
 * 
 */
@Data
public class RegisterModel {
	@NotEmpty(message = "メールアドレスを入力してください")
	private String email;
}
