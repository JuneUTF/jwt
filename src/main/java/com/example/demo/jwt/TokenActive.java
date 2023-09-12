package com.example.demo.jwt;

import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * トークンの生成と有効性を管理するクラスです。
 */
@Component
public class TokenActive {
    /**
     * トークンの有効期限（秒）を示すプロパティです。
     */
    @Value("${myapp.timeKeyNumber}")
    private int timeKeyNumber;

    /**
     * ランダムな数字と現在の時間スタンプを組み合わせてトークンキーを生成します。
     *
     * @return 生成されたトークンキー
     */
    public String creatKeyNumber() {
        String keyNumber ="";
        for (int i=0;i<6;i++) {
        	// 0から9までのランダムな数字を生成してキーに追加
            keyNumber+=new Random().nextInt(10); 
        }
        // 現在の時間をキーに追加
        keyNumber+="."+Instant.now();
        return keyNumber;
    }

    /**
     * 与えられたトークンの有効期限を確認します。
     *
     * @param timeToken トークンの時間情報を含む文字列
     * @return トークンが有効期限内である場合はtrue、それ以外はfalse
     */
    public Boolean checkTimeToken(String timeToken) {
    	// 現在の時間を取得
        Instant now = Instant.now(); 
        // トークンから時間情報を抽出してInstantオブジェクトに変換
        Instant t500s = Instant.parse(timeToken.substring(timeToken.indexOf(".") + 1));
        // 現在時刻とトークンの時間の差を計算（秒単位）
        long diffInSeconds = now.getEpochSecond() - t500s.getEpochSecond(); 
        // 差をコンソールに出力
        System.out.println(diffInSeconds); 
        // 差が有効期限内かどうかを確認して結果を返す
        return diffInSeconds < timeKeyNumber; 
    }
}
