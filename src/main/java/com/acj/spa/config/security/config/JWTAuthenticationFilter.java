package com.acj.spa.config.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acj.spa.dto.LoginDTO;
import com.acj.spa.config.security.JWTUtil;
import com.acj.spa.config.security.UsuarioSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDTO userEntity  = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getSenha()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,	HttpServletResponse response,FilterChain filterChain,Authentication auth) throws IOException, ServletException {

	    String username = ((UsuarioSecurity) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		
		response.getWriter().write(JWTUtil.TOKEN_PREFIX + token);
		response.addHeader(JWTUtil.HEADER_STRING, JWTUtil.TOKEN_PREFIX + token );
	}
}
