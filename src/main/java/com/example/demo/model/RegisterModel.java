package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterModel {
	@NotEmpty(message = "ユーザー名を入力してください")
    @Size(max = 50 , message = "ユーザー名は6～50桁以内で入力してください")
	@Size(min=6, message = "ユーザー名は6～50桁以内で入力してください")
	private String username;
	@NotEmpty(message = "パスワードを入力してください")
    @Size(max =16, message = "パスワードは8～16桁以内で入力してください")
    @Size(min=8, message = "パスワードは8～16桁以内で入力してください")
	private String password;
	private String passwordEncode;
	@NotEmpty(message = "メールアドレスを入力してください")
	private String email;
	@NotEmpty(message = "名前を入力してください")
    @Size(max =  255, message = "名前は255桁以内で入力してください")
	private String name;
	@NotEmpty(message = "住所を入力してください")
    @Size(max=255, message = "住所は255桁以内で入力してください")
	private String address;
	@NotEmpty(message = "生年月日を入力してください")
	private String birthday;
	@NotEmpty(message = "性別を選択してください")
	private String sex;
	private String keynumber;
}
