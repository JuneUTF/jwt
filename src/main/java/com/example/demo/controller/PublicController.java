package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.jwt.TokenActive;
import com.example.demo.model.HttpStatusCodeModel;
import com.example.demo.model.RequetsModel.ForgetPassModel;
import com.example.demo.model.RequetsModel.LoginModel;
import com.example.demo.model.ResponseModel.ResponseLoginModel;
import com.example.demo.model.ResponseModel.ResponseMessager;
import com.example.demo.requetsUrl.PubLicURL;
import com.example.demo.service.ForgetPassService;
import com.example.demo.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping(value = PubLicURL.keyRole)
public class PublicController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private LoginService userDetailsService;
	@Autowired
	private TokenActive tokenActive;
	@Autowired
	private ForgetPassService activeAndForgotPassService;
	/**ログインAPIクラス
	 * @return username,status,roles,token**/
	@PostMapping(PubLicURL.loginURL)
	public ResponseEntity<?> Login(@RequestBody LoginModel loginModel) throws AuthenticationException {

		try {
			//現在ログインしているアカウントの値を設定します。
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
			//SecurityContextHolder に認証値を設定する
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//LoginServiceインタフェース の selectLoginByUsername メソッドを呼び出してデータベース内のデータを取得します
			LoginModel userDetails = userDetailsService.selectLoginByUsername(loginModel.getEmail());
			ResponseLoginModel response = new ResponseLoginModel();
			response.setEmail(userDetails.getEmail());
			response.setRoles(userDetails.getRoles());
			response.setStatus(userDetails.getStatus());
			// jwtTokenUtilクラスのgenerateTokenメソッドを呼び出してトークンを作成します。
			final String token = jwtTokenUtil.generateToken(userDetails);
			//userDetails内にトークンを設定します。
			response.setToken(token);
			//レスポンスにuserDetailsを返します。
			return ResponseEntity.status(HttpStatusCodeModel.OK).body(response);
			
			
		} catch (AuthenticationException e) {
			//アカウントのパスワードが間違っている場合は、メッセージとともに 401 (不正なデータ) を返します。
			ResponseMessager responseMessager = new ResponseMessager();
			responseMessager.setMessager("パスワードまたはユーザー名が間違います。");
			return ResponseEntity.status(HttpStatusCodeModel.UNAUTHORIZED).body(responseMessager);
		}
	}

	/**パスワード再設定のキー番号の作成クラス
	 * 
	 * @param メール
	 * @param 確認番号
	 * @return　キー番号
	 */
	@PostMapping(PubLicURL.forgotpasswordURL)
	public ResponseEntity<?> forgotpassword(@RequestBody ForgetPassModel forgotPassModel) {
		try {
			//メールの検証
			//キー番号の生成
			String keyNumber = tokenActive.creatKeyNumber();
			System.out.println(keyNumber);
//			keyNumberModel.setContact("");
//		tiến hành ghi mã này vào csdl 
//		gửi mã này cho email
			forgotPassModel.setKeynumber(keyNumber);
			return ResponseEntity.ok("確認番号が作成されました。");
		} catch (Exception e) {
			ResponseMessager responseMessager = new ResponseMessager();
			responseMessager.setMessager("Error");
			return ResponseEntity.status(HttpStatusCodeModel.UNAUTHORIZED).body(responseMessager);
		}
	}
	/**パスワード再設定
	 * 
	 * @param メール
	 * @param 確認番号
	 * @return　Messager
	 */
	@PostMapping(PubLicURL.setpasswordURL)
	public ResponseEntity<?> setPassword() {
		try {
//			lấy email  trong csql
//			so sánh 2 mã với nhau
//			kiểm tra hạn của mã
//			kiểm tra 2 pass gửi lên xem đúng không
//			thay đổi pass và xóa mã trong csdl
			return ResponseEntity.ok("forgotpassword");
		} catch (Exception e) {
			return ResponseEntity.ok("forgotpassword");
		}
//		lấy mã trong csql
//		so sánh 2 mã với nhau
//		kiểm tra hạn của mã
//		kiểm tra 2 pass gửi lên xem đúng không
//		thay đổi pass và xóa mã trong csdl
	}

	/**国籍を返します。**/
	@PostMapping("/country")
	public ResponseEntity<?> country() {
		return ResponseEntity.ok("Country");
	}
	
	/**在留資格を返します。**/
	@PostMapping("/qualification")
	public ResponseEntity<?> qualification() {
		return ResponseEntity.ok("qualification");
	}
}