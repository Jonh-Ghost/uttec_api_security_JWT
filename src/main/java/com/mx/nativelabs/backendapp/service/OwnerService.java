package com.mx.nativelabs.backendapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;

public interface OwnerService extends BaseService<OwnerDTO>, UserDetailsService{
	/**
	 * Obtener sesi&oacute;n de Porpietario
	 * @param administradorDTO
	 * @return
	 * @throws ServiceException
	 */
	OwnerDTO obtenerSesionOwner(OwnerDTO ownerDTO) throws ServiceException;
	
	/**
	 * Consultar sesi&oacute;n cliente
	 * @param clienteDTO
	 * @return
	 * @throws ServiceException
	 */
	OwnerDTO consultOwnerEmail(String email) throws ServiceException;

	OwnerDTO create(OwnerDTO ownerDTO) throws ServiceException;

}
