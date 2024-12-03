package com.mx.nativelabs.backendapp.config.utils;

public class ConstantsSecurityConfig {
	
	private ConstantsSecurityConfig() {
		throw new IllegalStateException();
	}
	
	/**
	 * Prefijo del header Authorization es Bearer
	 */
	public static final String CABECERA_AUTORIZACION_PREFIJO_BEARER = "Bearer";
	
	/**
	 * Constrasenia signature JWT
	 */
	public static final String PASS_SIGNATURE = "UtTeC";
	
	/**
	 * Role claim property key in token
	 */
	public static final String AUTHORITIES_ROLE_KEY = "role";

}
