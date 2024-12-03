package com.mx.nativelabs.backendapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;

/**
 * AdministradorService.class
 * @author Diego Moreno S&aacute;nchez
 * @date 04/08/2020
 * @company Nativelabs 2020
 */
public interface AdministradorService extends BaseService<AdminDTO>, UserDetailsService{
	
	/**
	 * Obtener sesi&oacute;n de administrador
	 * @param administradorDTO
	 * @return
	 * @throws ServiceException
	 */
	AdminDTO obtenerSesionAdmin(AdminDTO adminDTO) throws ServiceException;
	
	/**
	 * Consultar sesi&oacute;n admin
	 * @param clienteDTO
	 * @return
	 * @throws ServiceException
	 */
	AdminDTO consultAdminEmail(String email) throws ServiceException;

	//AdminDTO getById(int id) throws ServiceException;
}						