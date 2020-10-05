package com.acj.spa.config.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acj.spa.config.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final CustomUserDetailService customUserDetailService;
	private final JWTUtil jwtUtil;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailService customUserDetailService, JWTUtil jwtUtil) {
		super(authenticationManager);
		this.customUserDetailService = customUserDetailService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
		String header = request.getHeader(JWTUtil.HEADER_STRING);
		
		if (header != null && header.startsWith(JWTUtil.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(header.substring(6));

            if (authenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
		}

		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

		if (!jwtUtil.tokenValido(token)) return null;

		String username = jwtUtil.getUsername(token);
		UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
