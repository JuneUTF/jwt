package com.example.demo.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.config.UserService;
import com.example.demo.model.HttpStatusCodeModel;
import com.example.demo.model.TokenErrorMessager;
import com.example.demo.requetsUrl.PubLicURL;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;
	@Value("${myapp.Bearer}")
	private String Bearer;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String requestURI = request.getRequestURI();
		String email = null;
		String jwtToken = null;
		response.setStatus(HttpStatusCodeModel.UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8"); // Đặt mã ký tự là UTF-8
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (PubLicURL.keyRole.equals(requestURI.substring(0,PubLicURL.keyRole.length()))) {
            chain.doFilter(request, response);
            return;
        }else if(requestTokenHeader != null && requestTokenHeader.startsWith(Bearer)) {
			jwtToken = requestTokenHeader.substring(Bearer.length());
			try {
				email = jwtTokenUtil.getEmailFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				logger.error("Unable to get JWT Token");
				response.getWriter().write(TokenErrorMessager.createTokenNotFoundError);
	            return;
			} catch (ExpiredJwtException e) {
	            response.getWriter().write(TokenErrorMessager.createTokenInvalidError);
	            return;
				
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
            response.getWriter().write(TokenErrorMessager.createBearerInvalidError);
            return;
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
		        // Token đã hết hạn
				response.setStatus(HttpStatusCodeModel.BAD_GATEWAY);
	            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	            response.getWriter().write(TokenErrorMessager.createBadGatewayError);
	            return;
		    }
		}
		chain.doFilter(request, response);
	}

}