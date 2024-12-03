package com.mx.nativelabs.backendapp.commons.constants;

/**
 * Constantes gobales para la Bakend demo app
 * @author Nativelabs21
 *
 */
public interface Constants {
	
	/**
	 * Prefijo de exito
	 */
	public static final String PREFIJO = "exito.codigo.";

	/**
	 * Constante que se usa para definir al usuario como cliente
	 */
	public static final String USUARIO_CLIENTE = "Owner";

	/**
	 * Constante que se usa para definir al usuario como administrador
	 */
	public static final String USUARIO_ADMINISTRADOR = "Administrador";
	
	/**
	 * Entidad de negocio llave cabecera de autorizaci&oacute;n
	 */
	public static final String LLAVE_CABECERA_AUTORIZACION = "Authorization";
	
	/**
	 * Cadena vacia para validacioines
	 */
	public static final String STRNG_EMPTY = "";

	/**
	 * Identificador para el tipo de usuario cliente
	 */
	public static final int ID_TYPE_USER_CUSTOMER = 1;

	/**
	 * Identificador para el estado del usuario registrado
	 */
	public static final int ID_STATUS_USER_REGISTRED = 1;

	/**
	 * Identificador para el estado del usuario eliminado
	 */
	public static final int ID_STATUS_USER_DELETED = 2;

	/**
	 * Identificador para el estado del usuario certificado
	 */
	public static final int ID_STATUS_USER_CETIFICATE = 3;

	/**
	 * Identificador para el estado del usuario activo
	 */
	public static final int ID_STATUS_USER_ACTIVE = 4;

	/**
	 * Identificador para el estado del usuario inactivo
	 */
	public static final int ID_STATUS_USER_INACTIVE = 5;
	
	/**
	 * Identificador para el estado de la sucursal activa
	 */
	public static final int ID_STATUS_BRANCHOFFICE_ACTIVE = 1;

	/**
	 * Identificador para el estado de la suursal inactiva
	 */
	public static final int ID_STATUS_BRANCHOFFICE_INACTIVE = 2;
	
	/**
	 * Identificador para el estado de la sucursal eliminada
	 */
	public static final int ID_STATUS_BRANCHOFFICE_DELETED = 3;
	
	/**  
	* Identificador para el estado DEL NEGOCIO
	 */
	public static final int ID_TYPE_BUSINESS_CAFETERIA = 1;

	/**
	 * Identificador para el estado del DEL NEGOCIO
	 */
	public static final int ID_TYPE_BUSINESS_BAR = 2;

	/**
	 * Identificador para el estado del NEGOCIO
	 */
	public static final int ID_TYPE_BUSINESS_FONDA = 3;
	
	/**
	 * Identificador para el estado de la sucursal activa
	 */
	public static final int ID_STATUS_MENU_ACTIVE = 4;

	/**
	 * Identificador para el estado de la suursal inactiva
	 */
	public static final int ID_STATUS_MENU_INACTIVE = 5;
	
	/**
	 * Identificador para el estado de la sucursal eliminada
	 */
	public static final int ID_STATUS_MENU_DELETED = 2;
	
	/**
	 * 
	 */
	public static final int ID_STATUS_PRODUCT_DELETED = 2;

	/**
	 * Constante de un minuto en milisegundos
	 */
	public static final long MINUTO_EN_MILISEGUNDOS = 60000;

	/**
	 * Constante de tiempo de vida de seguridad de un token en minutos
	 */
	public static final int TIME_OF_LIFE_TOKEN_SECURITY_IN_MIN = 5;

	/**
	 * Constante de tiempo de vida de seguridad de un token en d&iacute;as
	 */
	public static final int TIME_OF_LIFE_TOKEN_SECURITY_IN_DAY = 10;
	
	/**
	 * C&oacute;digo de &eacute;xito inicio de sesi&oacute;n
	 */
	public static final int INICIO_SESION_EXITOSA = 1006;

	/**
	 * Constante de llave AES
	 */
	public static final String KEY_SPECK_AES = "AES";

	/**
	 * Constante del algoritmo de encriptaci&oacute;n
	 */
	public static final String ALGORITMO_ENCRIPTACION = "AES/CBC/PKCS5Padding";

	/**
	 * Constante de codificaci&oacute;n utf-8
	 */
	public static final String CODIFICACION_UTF8 = "UTF-8";

	/**
	 * Constante de llave cifrada AES
	 */
	
	public static final String AES_CRYPTO_KEY = "5DCC67393750523CD165F17E1EFADD21";
	/**
	 * Constante del formato de c&oacute;digo con seis d&iacute;gitos
	 */
	public static final String STRING_FORMAT_SEIS_DIGITOS = "%06d";

	/**
	 * Constante de m&aacute;ximo n&uacute;mero aleatorio
	 */
	public static final int MAX_RANDOM = 999999;
	
	/**
	<<<<<<< HEAD
		 * C&oacute;digo de usuario no existente
		 */
		public static final int CUENTA_NO_EXISTE = 2006;
		
		/**
		 * C&oacute;digo de error de no existe
		 */
		public static final int NO_EXISTE = 2010;

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
		 * C&oacute;digo de &eacute;xito al crear un nuevo registro
		 */
		public static final int CREAR = 1003;

		/**
		 * C&oacute;digo de &eacute;xito al actualizar un nuevo registro
		 */
		public static final int ACTUALIZAR = 1004;

		/**
		 * C&oacute;digo de &eacute;xito al eliminar
		 */
		public static final int ELIMINAR = 1005;
		
		/**  
		* Identificador para el tipo DEL MENU/PRODUCTO
		 */
		public static final int ID_TYPE_MENU_PRODUCT_ALIMENTO = 1;

		/**
		 * Identificador para el tipo DEL MENU/PRODUCTO
		 */
		public static final int ID_TYPE_MENU_PRODUCT_BEBIDA = 2;

		/**
		 * Identificador para tipo DEL MENU/PRODUCTO
		 */
		public static final int ID_TYPE_MENU_PRODUCT_COCTEL = 3;
		
		/**
		 * Identificador para el tipo DEL MENU/PRODUCTO
		 */
		public static final int ID_TYPE_MENU_PRODUCT_POSTRE = 4;
		
		/**
		 * Estatus de historicos activos de qr
		 */
		public static final boolean STATUS_HISTORYC_QR_ACTIVE = true;
		
		/**
		 * Estatus de historicos inactivos de qr 
		 */
		public static final boolean STATUS_HISTORYC_QR_INACTIVE = false;

		



}
