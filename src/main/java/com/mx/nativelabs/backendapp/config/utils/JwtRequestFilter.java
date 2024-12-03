package com.mx.nativelabs.backendapp.config.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.service.AdministradorService;
import com.mx.nativelabs.backendapp.service.OwnerService;
import com.mx.nativelabs.backendapp.service.impl.AdministradorServiceImpl;
import com.mx.nativelabs.backendapp.service.impl.OwnerServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	private static final Logger logger = Logger.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AdministradorService administradorService;
	
	@Autowired
	private AdministradorServiceImpl administradorServiceImpl;
	
	@Autowired
	private OwnerService ownerservice;
	
	@Autowired
	private OwnerServiceImpl ownerserviceImpl;

	/**
	 * Valida el token de los request a /api/**
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith(ConstantsSecurityConfig.CABECERA_AUTORIZACION_PREFIJO_BEARER)) {
			jwtToken = requestTokenHeader.replaceAll(ConstantsSecurityConfig.CABECERA_AUTORIZACION_PREFIJO_BEARER, "").trim();
			logger.info("Token request - " + jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.error("IllegalArgumentException - " + e.getMessage());
			} catch (ExpiredJwtException e) {
				logger.error("ExpiredJwtException - " + e.getMessage());
			}
		} else {
			logger.info("Error en el prefijo");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			final String typeRole = jwtTokenUtil.getRoleAuthorityFromToken(jwtToken);
			
			logger.info("Rol del este usuario desde el token: " + typeRole);
			
			UserDetails userDetails = null; 
			
			logger.info("---------------------------------------------- tipo de rol " + typeRole);
			
			if (Constants.USUARIO_ADMINISTRADOR.equals(typeRole)) {
				userDetails = this.administradorServiceImpl.loadUserByUsername(username);
			} 
			if(Constants.USUARIO_CLIENTE.equals(typeRole)) {
				userDetails = this.ownerserviceImpl.loadUserByUsername(username);
		    }
			
			
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		
		chain.doFilter(request, response);
	}

}
