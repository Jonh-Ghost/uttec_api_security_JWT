package com.mx.nativelabs.backendapp.config.utils;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest paramHttpServletRequest,
						HttpServletResponse paramHttpServletResponse,
						AuthenticationException paramAuthenticationException) throws IOException, ServletException {
		logger.info("Fallo el metodo commence");
		paramHttpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
