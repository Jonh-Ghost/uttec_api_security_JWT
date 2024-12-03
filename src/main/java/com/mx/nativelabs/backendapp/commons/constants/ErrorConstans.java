package com.mx.nativelabs.backendapp.commons.constants;

public interface ErrorConstans {
	
	/**
	 * Prefijo de error
	 */
	public static final String PREFIJO = "error.codigo.";

	/**
	 * Prefijo de error
	 */
	public static final String PREFIX = "error.message.";

	/**
	 * C&oacute;digo gen&eacute;rico de error
	 */
	public static final int GENERIC = 2000;

	/**
	 * C&oacute;digo de error al consultar por id
	 */
	public static final int CONSULTAR_POR_ID = 2001;

	/**
	 * C&oacute;digo de error al consultar por filtros
	 */
	public static final int CONSULTAR_POR_FILTRO = 2002;

	/**
	 * C&oacute;digo de error al crear un nuevo registro
	 */
	public static final int CREAR = 2003;

	/**
	 * C&oacute;digo de error al actualizar
	 */
	public static final int ACTUALIZAR = 2004;

	/**
	 * C&oacute;digo de error al eliminar
	 */
	public static final int ELIMINAR = 2005;

	/**
<<<<<<< HEAD
	 * C&oacute;digo de usuario no existente
	 */
	public static final int CUENTA_NO_EXISTE = 2006;

	/**
	 * C&oacute;digo correo incorrecto
	 */
	public static final int CORREO_INCORRECTO = 2006;

	/**
	 * C&oacute;digo de error de contrase&ntilde;a incorrecta
	 */
	public static final int CONTRASENA_INCORRECTA = 2007;


	/**
	 * C&oacute;digo de error de consulta de inicio de sesi&oacute;n
	 */
	public static final int INICIO_SESION = 2008;

	/**
	 * C&oacute;digo de error al recuperar contrase&ntilde;a
	 */
	public static final int RECUPERAR_CONTRASENA = 2009;

	/**
	 * C&oacute;digo de error de no existe
	 */
	public static final int NO_EXISTE = 2010;

}
