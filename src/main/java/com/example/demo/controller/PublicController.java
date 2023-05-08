package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.jwt.TokenActive;
import com.example.demo.model.ActiveAndForgotPassModel;
import com.example.demo.model.KeyNumberModel;
import com.example.demo.model.LoginModel;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.reponseModel.ResLoginModel;
import com.example.demo.model.reponseModel.ResponseMessager;
import com.example.demo.service.ActiveAndForgotPassService;
import com.example.demo.service.EmailService;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/api/public")
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
	private ActiveAndForgotPassService activeAndForgotPassService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//ログインAPI
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody LoginModel loginModel, ResLoginModel resLoginModel,
			ResponseMessager responseMessager) throws AuthenticationException {
		System.out.println(loginModel);

		try {
//			set giá trị tài khoản đang đăng nhập để sau này so sánh
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
//			hán giá trị authentication vào SecurityContextHolder
			SecurityContextHolder.getContext().setAuthentication(authentication);
//			gọi đến phương thức selectLoginByUsername ở trong userDetailsService để lấy dữ liệu trong database
			LoginModel userDetails = userDetailsService.selectLoginByUsername(loginModel.getUsername());
//			kiểm tra tình trạng active của user đã nhập
			if (userDetails.getStatus().equals("PENDDING")) {
//				đang pending trả về messager và lỗi 403(không có quyền truy cập)
				responseMessager.setMessager("お客様のアカウントはまだ有効になっていません。");
				return ResponseEntity.status(403).body(responseMessager);
			} else {
//				nếu đang active thì gán giá trị cần trả về trong ResLoginModel từ userDetails
				resLoginModel.setUsername(userDetails.getUsername());
				resLoginModel.setRoles(userDetails.getRoles());
//			tạo token với username đã đăng nhập
				final String token = jwtTokenUtil.generateToken(userDetails);
//			gán token vào ResLoginModel
				resLoginModel.setToken(token);
//			trả về đầy đủ ResLoginModel
				return ResponseEntity.ok(resLoginModel);
			}
		} catch (AuthenticationException e) {
//			nếu tài khoản mật khẩu không đúng trả về 401(dữ liệu không chính xác) kèm messager
			responseMessager.setMessager("パスワードまたはユーザー名が間違います。");
			return ResponseEntity.status(401).body(responseMessager);
		}
	}

//URLとしてアカウントは有効する 
	@PostMapping("/active")
	public ResponseEntity<?> activeUser(@RequestBody ActiveAndForgotPassModel activeAndForgotPassModel,
			ResponseMessager responseMessager) {
//		kiểm tra xem gửi lên có chữa mã xác nhận hay không
		if (activeAndForgotPassModel.getKeyToken() != null) {
			try {
//				
//				lấy mã xác nhận trong csdl ra
				ActiveAndForgotPassModel keyNumber = activeAndForgotPassService
						.selectKeyNumberByUsername(activeAndForgotPassModel.getUsername());
//				kiểm tra xem 2 mã có trùng khớp nhau không
				if (!activeAndForgotPassModel.getKeyToken().equals(keyNumber.getKeynumber().substring(0, 6))) {
					responseMessager.setMessager("認証番号内容が正しくない");
					return ResponseEntity.status(401).body(responseMessager);
				}
//				gọi hàm để check thời gian mã trong csdl còn hạn không
				if (tokenActive.checkTimeToken(keyNumber.getKeynumber())) {
					responseMessager.setMessager("認証番号が期限切れました");
					return ResponseEntity.status(401).body(responseMessager);
				}
//				 tiến hành kích hoạt user
				int updateOk = activeAndForgotPassService
						.updateActiveByUsername(activeAndForgotPassModel.getUsername());
//				kiểm tra kích hoạt được chưa
				if (updateOk != 1) {
					responseMessager.setMessager("アカウントは有効が失敗しました");
					return ResponseEntity.status(503).body(responseMessager);
				} else {
					responseMessager.setMessager("アカウントが有効できました");
					return ResponseEntity.ok().body(responseMessager);
				}
			} catch (Exception e) {
				responseMessager.setMessager("認証番号が正しくない");
				return ResponseEntity.status(401).body(responseMessager);
			}
		} else {
			responseMessager.setMessager("認証番号がありません");
			return ResponseEntity.status(401).body(responseMessager);
		}
	}

//    新規登録API
	@PostMapping("/register")
	public ResponseEntity<?> rigister(@Validated @RequestBody RegisterModel registerModel, BindingResult result,
			ResponseMessager responseMessager) {
		try {
			if (result.hasErrors()) {
				List<String> errorList = new ArrayList<String>();
				for (ObjectError error : result.getAllErrors()) {
					errorList.add(error.getDefaultMessage());
				}
				return ResponseEntity.status(401).body(errorList);
			}
//			check username và email xem tồn tại chưa
			RegisterModel checkModel = registerService.checkUserAndEmail(registerModel);
			if(checkModel != null) {
				responseMessager.setMessager("メールまたはユーザー名が存在しています。");
				return ResponseEntity.status(401).body(responseMessager);
			}
			System.out.println(checkModel);
//				tạo ra mã để ghi vào csdl
			String keyNumber = tokenActive.creatKeyNumber();
//			encode pass
			registerModel.setPasswordEncode(passwordEncoder .encode(registerModel.getPassword()));
//			set keynumber ghi vào csdl
			registerModel.setKeynumber(keyNumber);
//				tiến hành ghi vào cơ sở dữ liệu
			registerService.registerUsername(registerModel);
//			 tiến hành cắt mã và gửi đi trong mail kích hoạt
			registerModel.setKeynumber(keyNumber.substring(0, 6));
			emailService.RegisterEmail(registerModel);
			responseMessager.setMessager("SUCCESS");
			return ResponseEntity.status(401).body(responseMessager);
		} catch (MessagingException e) {
			e.printStackTrace();
			responseMessager.setMessager("DEFEATED");
			return ResponseEntity.status(401).body(responseMessager);
		}
	}

//  パスワードを忘れた
	@PostMapping("/forgotpassword")
	public ResponseEntity<?> createKeyNumber(@RequestBody KeyNumberModel keyNumberModel,
			ResponseMessager responseMessager) {
		try {
//		gọi hàm tạo mã lấy lại pass.
			String keyNumber = tokenActive.creatKeyNumber();
//			keyNumberModel.setContact("");
//		tiến hành ghi mã này vào csdl 
//		gửi mã này cho email
			keyNumberModel.setKeynumber(keyNumber);
			return ResponseEntity.ok("確認番号が作成されました。");
		} catch (Exception e) {
			responseMessager.setMessager("Error");
			return ResponseEntity.status(401).body(responseMessager);
		}
	}

	@PostMapping("/setpassword")
	public ResponseEntity<?> setPassword(ResponseMessager responseMessager) {
//		lấy mã trong csql
//		so sánh 2 mã với nhau
//		kiểm tra hạn của mã
//		kiểm tra 2 pass gửi lên xem đúng không
//		thay đổi pass và xóa mã trong csdl
		return ResponseEntity.ok("forgotpassword");
	}

//権限種類
	@PostMapping("/roles")
	public ResponseEntity<?> roles(ResponseMessager responseMessager) {
		return ResponseEntity.ok("Roles");
	}

//国籍
	@PostMapping("/country")
	public ResponseEntity<?> country(ResponseMessager responseMessager) {
		return ResponseEntity.ok("Country");
	}
}