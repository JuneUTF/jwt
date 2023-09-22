package com.example.demo.model.RequetsModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * パスワード再設定リクエストモデル
 */
@Data
public class ForgetPasswordRequets {
    /**
     * メールアドレス
     */
	@NotEmpty(message = "メールアドレスは必須です")
    @Email(message = "有効なメールアドレスを入力してください")
    private String email;


    /**
     * トークン
     */
    private String token;
}
