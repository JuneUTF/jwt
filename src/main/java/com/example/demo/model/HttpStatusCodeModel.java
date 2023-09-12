package com.example.demo.model;

/**
 * HTTPステータスコードを定義するモデルクラスです。
 */
public class HttpStatusCodeModel {
    // HTTP 200 OK: リクエストが成功したことを示すステータスコード
    public static final int OK = 200;
    // HTTP 201 Created: リソースが正常に作成されたことを示すステータスコード
    public static final int CREATED = 201;
    // HTTP 204 No Content: レスポンスが本文を持たないことを示すステータスコード
    public static final int NO_CONTENT = 204;
    // HTTP 400 Bad Request: クライアントのリクエストが不正であることを示すステータスコード
    public static final int BAD_REQUEST = 400;
    // HTTP 401 Unauthorized: 認証が必要であることを示すステータスコード
    public static final int UNAUTHORIZED = 401;
    // HTTP 403 Forbidden: アクセスが拒否されたことを示すステータスコード
    public static final int FORBIDDEN = 403;
    // HTTP 404 Not Found: リソースが見つからなかったことを示すステータスコード
    public static final int NOT_FOUND = 404;
    // HTTP 405 Method Not Allowed: リクエストメソッドが許可されていないことを示すステータスコード
    public static final int METHOD_NOT_ALLOWED = 405;
    // HTTP 500 Internal Server Error: サーバー内部でエラーが発生したことを示すステータスコード
    public static final int INTERNAL_SERVER_ERROR = 500;
    // HTTP 502 Bad Gateway: ゲートウェイが不正なリクエストを受け取ったことを示すステータスコード
    public static final int BAD_GATEWAY = 502;
    // HTTP 503 Service Unavailable: サービスが一時的に利用できないことを示すステータスコード
    public static final int SERVICE_UNAVAILABLE = 503;
    // HTTP 504 Gateway Timeout: ゲートウェイがタイムアウトしたことを示すステータスコード
    public static final int GATEWAY_TIMEOUT = 504;
    // HTTP 429 Too Many Requests: クライアントが制限を超えるリクエストを送信したことを示すステータスコード
    public static final int TOO_MANY_REQUESTS = 429;
}
