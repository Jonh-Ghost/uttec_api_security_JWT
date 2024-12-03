package com.mx.nativelabs.backendapp.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.service.AdministradorService;
import com.mx.nativelabs.backendapp.service.LoginService;
import com.mx.nativelabs.backendapp.service.OwnerService;

@Service
public class LoginServiceImpl implements LoginService {
		
	@Autowired
	AdministradorService administradorService;
	
	@Autowired 
	OwnerService ownerService;

	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Override
	public AdminDTO sesionAdministrador(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		AdminDTO inicioSesionDTo = new AdminDTO();
		try {
			logger.info("Datos de Administrador: " + adminDTO.toString());
			inicioSesionDTo = administradorService.obtenerSesionAdmin(adminDTO);	
			logger.info("Objeto de la sesi&otilde;n: " + inicioSesionDTo.toString());
			
		} catch (ServiceException e) {
			logger.error("Sec : ", e);
			e.printStackTrace();
			
		} catch (Exception e) {
			logger.info("Ha ocurrido un error en el servicio de login E: " + e.getCause());
			logger.error("e : " + e, e);
			e.printStackTrace();
		}
		return inicioSesionDTo;
	}
	
	@Override
	public OwnerDTO sesionCliente(OwnerDTO ownerDTO) throws ServiceException {
		OwnerDTO inicioSesionDTo = new OwnerDTO();
		try {

			logger.info("Datos del usuario: " + ownerDTO.toString());
			inicioSesionDTo = ownerService.obtenerSesionOwner(ownerDTO);
			logger.info("Objeto de la sesion: " + inicioSesionDTo.toString());

		} catch (ServiceException e) {
			logger.error("Sec : ", e);
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			logger.info("Ha ocurrido un error en el servicio de login E: " + e.getCause());
			logger.error("e : " + e, e);
			e.printStackTrace();
		}
		return inicioSesionDTo;
	}

}
