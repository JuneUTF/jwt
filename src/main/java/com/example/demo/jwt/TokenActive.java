package com.example.demo.jwt;

import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenActive {
//	// tọa mã khóa bí mật
//		@Value("${myapp.secretKey}")
//		private String secretKey;
//	public String createToken(String username, Number tokennumber) {
//		Claims claims = Jwts.claims();
//		claims.put("username", username);
//		claims.put("tokennumber", tokennumber);
//		Date now = new Date();
//		Date expirationDate = new Date(now.getTime() + 300 * 1000);
//		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expirationDate)
//				 .signWith(SignatureAlgorithm.HS512,secretKey).compact();
//	}
//
//	public  String getUsername(String token) {
//		Claims claims = getClaimsFromToken(token);
//		return claims.get("username", String.class);
//	}
//
//	public  Number getTokennumber(String token) {
//		Claims claims = getClaimsFromToken(token);
//		return claims.get("tokennumber", Number.class);
//	}
//
//	public  boolean isTokenExpired(String token) {
//		Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//
//	private Claims getClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//	}
//
//	private Date getExpirationDateFromToken(String token) {
//		Claims claims = getClaimsFromToken(token);
//		return claims.getExpiration();
//	}
	@Value("${myapp.timeKeyNumber}")
	private int timeKeyNumber;
//	tạo keynumber với từ đầu tiên là A là active user và R là lấy lại MK
	public String creatKeyNumber() {
		String keyNumber ="";
		for (int i=0;i<6;i++) {
			keyNumber+=new Random().nextInt(10);
		}
		keyNumber+="."+Instant.now();
		return keyNumber;
	}
	public Boolean checkTimeToken(String timeToken) {
	    Instant now = Instant.now();
	    Instant t500s = Instant.parse(timeToken.substring(timeToken.indexOf(".") + 1));
	    long diffInSeconds = now.getEpochSecond() - t500s.getEpochSecond();
	    System.out.println(diffInSeconds);
	    return diffInSeconds>timeKeyNumber;
	}
	
	
	
}
