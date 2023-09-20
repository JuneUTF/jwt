package com.example.demo.model.ResponseModel;


import lombok.Data;

/**
 * ログインモデルクラスは、認証およびユーザー情報の保持に使用されます
 */
@Data
public class ResponseLoginModel {

    /**
     * ユーザー名
     */
    private String email;

    /**
     * パスワードはセキュリティのために保護され、JSON変換時に無視されます。
     */
    private String status;

    /**
     * ユーザーのロールや権限情報を示す文字列です。
     */
    private String roles;

    /**
     * 認証トークンはログイン後に生成され、セッションの状態管理に使用されます。
     */
    private String token;
}
