package com.mx.nativelabs.backendapp.commons.model.response;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;


public class BaseResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	private String message;
	
	private transient Object datos;
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getDatos() {
		return datos;
	}

	public void setDatos(Object datos) {
		this.datos = datos;
	}
	
}
