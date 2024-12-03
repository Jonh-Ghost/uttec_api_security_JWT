package com.mx.nativelabs.backendapp.controller;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.config.utils.Authenticator;
import com.mx.nativelabs.backendapp.config.utils.ConstantsSecurityConfig;
import com.mx.nativelabs.backendapp.config.utils.JwtTokenUtil;
import com.mx.nativelabs.backendapp.repository.OwnerRepository;
import com.mx.nativelabs.backendapp.service.AdministradorService;
import com.mx.nativelabs.backendapp.service.LoginService;
import com.mx.nativelabs.backendapp.service.OwnerService;
import com.mx.nativelabs.backendapp.service.impl.AdministradorServiceImpl;
import com.mx.nativelabs.backendapp.service.impl.OwnerServiceImpl;
@RestController
@RequestMapping("/api/v1/sesion")
@CrossOrigin
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	AdministradorService administradorService;
	
	@Autowired 
	OwnerServiceImpl ownerServiceImpl;
	
	@Autowired
	AdministradorServiceImpl administradorServiceImpl;

	@Autowired
	private LoginService loginService;

	@Autowired
	Authenticator authenticator;
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	private OwnerRepository ownerRepository;

	@ResponseBody
	@PostMapping(value="/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> LoginAdmin(@RequestBody AdminDTO adminDTO) {
		logger.info("Entrando al serivicio logiin admin"+ adminDTO.toString());
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		String email = adminDTO.getEmail();
		String tokenHeaderResponse = null;
		try {
			authenticator.authenticateAdministrador(adminDTO.getEmail(), adminDTO.getPassword());
			logger.info("Iniciar session para: " + adminDTO.toString());
			logger.info("Iniciar session para: " + adminDTO.getPassword().toString());
			final UserDetails userDetails = administradorService.loadUserByUsername(email);
			logger.info("esto dice user detalis ADMIN!!!: "+userDetails );
			AdminDTO loginAdminDTO = loginService.sesionAdministrador(adminDTO);
			final String token = jwtTokenUtil.generarToken(userDetails, loginAdminDTO);
			tokenHeaderResponse = ConstantsSecurityConfig.CABECERA_AUTORIZACION_PREFIJO_BEARER + " " + (token.trim());
			logger.info(" TOKEN:     " + token);

			loginAdminDTO.setToken(token);
			baseResponseDTO.setDatos(loginAdminDTO);
			baseResponseDTO.setCode(Constants.INICIO_SESION_EXITOSA);

		} catch (ServiceException e) {
			logger.info("Codigo de Error: " + e.getIdError());
			baseResponseDTO.setCode(e.getIdError());
			baseResponseDTO.setMessage(messageSource.getMessage(Constants.PREFIJO + e.getIdError(),
					new Object[] { adminDTO.getEmail() }, LocaleContextHolder.getLocale()));
			logger.info("Mensaje de Error: " + baseResponseDTO.getMessage());

			logger.error("Ha ocurrido un error en el login de usuario SE (controller): " + e.getCause());
			logger.error("e : " + e, e);
		}
		
		return ResponseEntity.status(HttpStatus.OK).header("Authorization", tokenHeaderResponse).body(baseResponseDTO);
	}

	@ResponseBody
	@PostMapping(value ="/owner", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> inicioSesionCliente(@RequestBody OwnerDTO ownerDTO) {
		
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		String tokenHeaderResponse = null;
		String password =  ownerDTO.getPassword().toString();
		String email= ownerDTO.getEmail().toString();
		logger.info("el password es: " + password);
		logger.info("El usuario es: " + email);
		Optional<OwnerDO> ownerDO = ownerRepository.findByPasswordAndEmail(password, email);
		logger.info("Esto es lo que imprime el buscar password	" + ownerDO.toString());
		try {
			
			authenticator.authenticateOwner(ownerDTO.getEmail(), ownerDTO.getPassword());
			logger.info("Iniciar sesi&otilde;n para: " + ownerDTO.toString());
			logger.info("--------------------------------------------------- login controller " +email );
			final UserDetails userDetails = ownerServiceImpl.loadUserByUsername(email);
			logger.info("esto dice user detalis: OWNER!!!! "+userDetails );
			OwnerDTO inicioSesionDTO = loginService.sesionCliente(ownerDTO);
			final String token = jwtTokenUtil.generarTokenOwner(userDetails, inicioSesionDTO);

			tokenHeaderResponse = ConstantsSecurityConfig.CABECERA_AUTORIZACION_PREFIJO_BEARER + " " + (token.trim());
			logger.info("	" + token);
				
			inicioSesionDTO.setToken(token);
			baseResponseDTO.setDatos(inicioSesionDTO);
			baseResponseDTO.setCode(Constants.INICIO_SESION_EXITOSA);
			
		} catch (ServiceException e) {
			logger.info("Codigo de Error: " + e.getIdError());
			baseResponseDTO.setCode(e.getIdError());
			baseResponseDTO.setMessage("El usuario " + messageSource.getMessage(Constants.PREFIJO + e.getIdError(),
					new Object[] { ownerDTO.getEmail() }, LocaleContextHolder.getLocale()));
			
			logger.info("Mensaje de Error: " + baseResponseDTO.getMessage());

			logger.error("Ha ocurrido un error en el login de usuario SE (controller): " + e.getCause());
			logger.error("e : " + e, e);
		}

		return ResponseEntity.status(HttpStatus.OK).header("Authorization", tokenHeaderResponse).body(baseResponseDTO);
	}
}
