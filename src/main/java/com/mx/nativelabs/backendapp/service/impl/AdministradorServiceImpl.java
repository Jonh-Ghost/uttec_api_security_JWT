package com.mx.nativelabs.backendapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.AdminMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.AdminDO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.repository.AdminRepository;
import com.mx.nativelabs.backendapp.service.AdministradorService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Service
public class AdministradorServiceImpl implements AdministradorService {

	private static final Logger logger = Logger.getLogger(AdministradorServiceImpl.class);

	@Autowired
	private AdminRepository administradorRepository;
	
	//@Autowired
	//private AdminCustomRepository adminCustomRepository;

	@Autowired
	PasswordEncoder sCryptPasswordEncoder;
	
	@Override
	public AdminDTO getById(int id) throws ServiceException {
		AdminDTO adminDTO = null;
		try {
			logger.info("Consultar propietario con id: " + id);
			Optional<AdminDO> adminDO = administradorRepository.findById(id);
			
			if (!adminDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El propietario no existe");
			}

			adminDTO = AdminMapper.toGet(adminDO.get());
			logger.info("Consulta de propietario: " + adminDTO);

		} catch (ServiceException se) {
			logger.error("ServiceException: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		
		return adminDTO;
		
	}
	
//	@Override
//	public UserDetails loadUserByUsernameAdmin(String email) throws UsernameNotFoundException {
//		logger.info("email:::"+email);
//		User user = null;
//		Optional<AdminDO> adminDO = administradorRepository.findByEmail(email);
//		logger.info("administradorDO:::"+adminDO);
//		if(adminDO == null) {
//			throw new UsernameNotFoundException(SpringSecurityMessageSource.getAccessor().getMessage(ErrorConstans.PREFIJO + ErrorConstans.CUENTA_NO_EXISTE, new Object[] {email}, "Administrador no Registrado"));
//		}
//		List<GrantedAuthority> autorityList = new ArrayList<GrantedAuthority>();
//		autorityList.add(new SimpleGrantedAuthority(Constants.USUARIO_ADMINISTRADOR));
//		user = new User(adminDO.get().getEmail(), adminDO.get().getPassword(), true, true, true, true, autorityList);
//
//		return user;
//	}

	@Override 
	public AdminDTO obtenerSesionAdmin(AdminDTO adminDTO) throws ServiceException {

		logger.info("Dentro del servicio");
		AdminDTO inicioSesionDTO = null;
		try {
			Optional<AdminDO> adminDO = administradorRepository.findByEmail(adminDTO.getEmail());
			logger.info("AdministradorDO::::{"+adminDO);
			if (adminDO != null) {
				inicioSesionDTO = AdminMapper.mapper(adminDO.get());
			}	
		} catch (Exception e) {
			logger.info("Ha ocurrido un error: " + e.getCause());
			logger.error("e : "+e,e);
			e.printStackTrace();
			//			throw new ServiceException("Usuario no registrado");
		}
		return inicioSesionDTO;
	}

	@Override
	public AdminDTO consultAdminEmail(String email) throws ServiceException {
		AdminDTO adminDTO = null;
		try{			
			Optional<AdminDO> adminDO = administradorRepository.findByEmail(email);
			if(adminDO.isPresent()){
				adminDTO = AdminMapper.mapper(adminDO.get());
			}			
		}catch (Exception e){
			logger.error("Error al consultar al cliente por correo");
			e.printStackTrace();
		}
		return adminDTO;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminDTO create(AdminDTO baseDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminDTO update(int id, AdminDTO baseDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("email:::"+email);
		User user = null;
		Optional<AdminDO> adminDO = administradorRepository.findByEmail(email);
		logger.info("administradorDO:::"+adminDO);
		if(!adminDO.isPresent()) {
			throw new UsernameNotFoundException(SpringSecurityMessageSource.getAccessor().getMessage(ErrorConstans.PREFIJO + ErrorConstans.CUENTA_NO_EXISTE, new Object[] {email}, "Administrador no Registrado"));
		}
		List<GrantedAuthority> autorityList = new ArrayList<GrantedAuthority>();
		autorityList.add(new SimpleGrantedAuthority(Constants.USUARIO_ADMINISTRADOR));
		user = new User(adminDO.get().getEmail(), adminDO.get().getPassword(), true, true, true, true, autorityList);

		return user;
	}

	

}
