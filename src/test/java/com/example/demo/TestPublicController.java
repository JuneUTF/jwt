package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.PublicController;
import com.example.demo.model.HttpStatusCodeModel;
import com.example.demo.model.RequetsModel.LoginModel;
import com.example.demo.requetsUrl.PubLicURL;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
public class TestPublicController {
	private PublicController publicController;
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    /**ログイン情報がただし場合
     * @throws Exception **/
	@Test
	public void CheckLoginTrue()  throws Exception {
		//ログイン情報の設定
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail("june.utf@gmail.com");
	    loginModel.setPassword("12345678");
	    //Json型の変更
        String loginJson = objectMapper.writeValueAsString(loginModel);
        //リクエストの送信
        ResultActions LoginTrue;
			LoginTrue = mockMvc.perform(
					//メソッドPOST、URLログインの設定
					MockMvcRequestBuilders.post(PubLicURL.keyRole+PubLicURL.loginURL)
					//データ型の定義
			        .contentType(MediaType.APPLICATION_JSON)
			        //パラメータの渡す
			        .content(loginJson));
			System.out.println( LoginTrue.andReturn());
			//リクエストの送信結果番号
			int statusCode = LoginTrue.andReturn().getResponse().getStatus();
			//期待結果と実行結果の比較
			assertEquals(statusCode, HttpStatusCodeModel.OK);
	}
	@Test
	public void CheckLoginFalse()throws Exception {
		//ログイン情報の設定
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail("test@gmail.com");
	    loginModel.setPassword("1234555678");
		//Json型の変更
        String loginJson = objectMapper.writeValueAsString(loginModel);
		//リクエストの送信
		ResultActions LoginFalse = mockMvc.perform(
		     //メソッドPOST、URLログインの設定
		     MockMvcRequestBuilders.post(PubLicURL.keyRole+PubLicURL.loginURL)
		     //データ型の定義
		     .contentType(MediaType.APPLICATION_JSON)
		     //パラメータの渡す
		     .content(loginJson));
		//リクエストの送信結果番号
		int statusCode = LoginFalse.andReturn().getResponse().getStatus();
		//期待結果と実行結果の比較
		assertEquals(statusCode, HttpStatusCodeModel.UNAUTHORIZED);
	}
}
