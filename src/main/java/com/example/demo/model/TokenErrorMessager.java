package com.example.demo.model;

/**
 * トークンエラーメッセージを生成するためのクラスです。
 */
public class TokenErrorMessager {

    /**トークンが存在しない場合のエラーメッセージ     */
	public static final String createTokenNotFoundError = "{ \"error\": \"トークンが存在しません\" }";
    /**トークンが無効である場合のエラーメッセージ*/
    public static final String createTokenInvalidError = "{ \"error\": \"トークンが無効です\" }";
    /**ベアラー文字列が正しくない場合のエラーメッセージ*/
    public static final String createBearerInvalidError = "{ \"error\": \"ベアラー文字列が正しくありません\" }";
    /**ゲートウェイが不正なリクエストを受け取ったことを示すステータスコード*/
    public static final String createBadGatewayError = "{ \"error\": \"ゲートウェイが不正なリクエストを受け取ったことを示すステータスコード\" }";
    }