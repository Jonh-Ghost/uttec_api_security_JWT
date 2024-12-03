package com.mx.nativelabs.backendapp.commons.exception;

import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;

/**
 * Custom exception para tratamiento de errores personalizados
 * @author Nativelabs21
 *
 */
public class ServiceException extends Exception {

	/**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador del Error
	 */
	private Integer idError;
	
	private String codigo;
	
	private String messageException;
	
	private String entidadNegocio;

	/**
	 * Constructor Base
	 */
	public ServiceException() {
		this.idError = ErrorConstans.GENERIC;
	}
	
	/**
	 * Constructor que recibe como parametro el
	 * mensaje de la Excepcion
	 * @param message
	 */
//	public ServiceException(String message) {
//		super(message);
//	}
	
	/**
	 * Constructor que recibe parametro message
	 */
	public ServiceException(Throwable throwable) {
		super(throwable);
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * Constructor que recibe id del error
	 */
	public ServiceException(Integer idError) {
		this.idError = idError;
	}
	
	/**
	 * Constructor
	 * @param throwable
	 * @param noError
	 */
	public ServiceException(Integer idError , Throwable throwable) {
		super(throwable);
		this.idError = idError;
		
	}
	
	public ServiceException(String codigo, Throwable throwable) {
		super(throwable);
		this.codigo = codigo;
		
	}
	
	/**
	 * Constructor
	 * @param throwable
	 * @param noError
	 */
	public ServiceException(Integer idError , Exception exception) {
		super(exception);
		this.idError = idError;
	}
	
	public ServiceException(String codigo, Exception exception) {
		super(exception);
		this.codigo = codigo;
	}
	
	/**
	 * Constructor
	 * @param idError the idError to set and Description
	 */
	public ServiceException(Integer idError, String messageException) {
		super(messageException);
		this.idError = idError;
		this.messageException = messageException;
	}
	public ServiceException(Integer idError, String messageException, String entidadNegocio) {
		super(messageException);
		this.idError = idError;
		this.messageException = messageException;
		this.setEntidadNegocio(entidadNegocio);
	}
	
	public ServiceException(String codigo, String messageException) {
		super(messageException);
		this.codigo = codigo;
		this.messageException = messageException;
	}
	
	
	/**
	 * @return the idError
	 */
	public Integer getIdError() {
		return idError;
	}

	/**
	 * @param idError the idError to set
	 */
	public void setIdError(Integer idError) {
		this.idError = idError;
	}

	public String getMessageException() {
		return messageException;
	}

	public void setMessageException(String messageException) {
		this.messageException = messageException;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEntidadNegocio() {
		return entidadNegocio;
	}

	public void setEntidadNegocio(String entidadNegocio) {
		this.entidadNegocio = entidadNegocio;
	}
	
}