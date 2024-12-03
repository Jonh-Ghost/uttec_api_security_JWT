package com.mx.nativelabs.backendapp.config.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.service.AdministradorService;
import com.mx.nativelabs.backendapp.service.OwnerService;

@Component
public class Authenticator {

	private static final Logger logger = Logger.getLogger(Authenticator.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	OwnerService clienteService;

	@Autowired
	AdministradorService administradorService;

	/**
	 * 
	 * @param email
	 * @param password
	 * @throws ServiceException
	 */
	public void authenticateOwner(String email, String password) throws ServiceException {
		try {
			logger.info("Correo " + email + " - Contrasenia: " + password);
			logger.info("**************** owner");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (UsernameNotFoundException unf) {
			logger.info("Error UserNotFoundException c: " + unf.getMessage());
		} catch (BadCredentialsException bc) {
			logger.info("Error BadCredentialsException authenticate c: " + bc.getMessage());
			logger.error("bc : " + bc, bc);
			OwnerDTO proveedorDTO = clienteService.consultOwnerEmail(email);
			int codigoError = 0;
			if (proveedorDTO == null) {
				codigoError = Constants.NO_EXISTE;
			} else {
				codigoError = Constants.CONTRASENA_INCORRECTA;
			}
			throw new ServiceException(codigoError, "Contrase&ntilde;a incorrrecta");

		} catch (Exception e) {
			logger.info("Ha ocurrido un error en el login c: " + e.getMessage());
			logger.error("e : " + e, e);
		}

	}

	/**
	 * 
	 * @param email
	 * @param password
	 * @throws ServiceException
	 */
	public void authenticateAdministrador(String email, String password) throws ServiceException {
		try {
			logger.info("Correo " + email + " - Contrasenia: " + password);

			logger.info("*************** admin");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

			logger.info("despues de entrar");
		} catch (UsernameNotFoundException unf) {
			logger.info("Error UserNotFoundException c: " + unf.getMessage());
		} catch (BadCredentialsException bc) {
			logger.info("Error BadCredentialsException authenticate c: " + bc.getMessage());
			logger.error("bc : " + bc, bc);

			AdminDTO adminDT = administradorService.consultAdminEmail(email);

			logger.info(
					"esto es el buscar correo admin......................................................................................................."
							+ adminDT);
			int codigoError = 0;
			if (adminDT == null) {
				codigoError = Constants.NO_EXISTE;
			} else {
				codigoError = Constants.CONTRASENA_INCORRECTA;
			}
			throw new ServiceException(codigoError, "Contrase&ntilde;a incorrecta");
		} catch (Exception e) {
			logger.info("Ha ocurrido un error en el login c: " + e.getMessage());
			logger.error("e : ", e);
		}

	}

}