package com.mx.nativelabs.backendapp.service;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;

public interface LoginService {

	/**
	 * Obtener sesi&oacute;n de administrador
	 * @param administradorDTO
	 * @return
	 */
	AdminDTO sesionAdministrador(AdminDTO administradorDTO) throws ServiceException;
	
	/**
	 * Obtener sesi&oacute;n de cliente
	 * @param proveedorDTO
	 * @return
	 * @throws ServiceException
	 */
	OwnerDTO sesionCliente(OwnerDTO ownerDTO) throws ServiceException;
	
}
