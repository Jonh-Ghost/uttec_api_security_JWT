package com.mx.nativelabs.backendapp.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.constants.SuccessConstants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseObjectDTO;
import com.mx.nativelabs.backendapp.config.utils.Authenticator;
import com.mx.nativelabs.backendapp.config.utils.ConstantsSecurityConfig;
import com.mx.nativelabs.backendapp.config.utils.JwtTokenUtil;
import com.mx.nativelabs.backendapp.service.OwnerService;
import com.mx.nativelabs.backendapp.service.impl.OwnerServiceImpl;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {
	
	private static final Logger logger = Logger.getLogger(OwnerController.class);
	
	@Autowired
	private OwnerServiceImpl ownerServiceImpl;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	Authenticator authenticator;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	/**
	 * Expone servicio de consulta propietario por id
	 * @param id Identificador del propietario
	 * @return Respuesta con el detalle del propietario
	 */
	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getById(@PathVariable("id") int id){
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			OwnerDTO ownerDTO = this.ownerService.getById(id);
			
			responseObjectDTO.setData(ownerDTO);
			responseObjectDTO.setCode(SuccessConstants.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch(ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		}
		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
	}


	/**
	 * Exponse servicio de consulta de propietario por criterios de b&uacute;suqeda
	 * @param allParams
	 * @return
	 */
	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getByFilter(@RequestParam Map<String, String> allParams) {
		ResponseListDTO listDTO = null;

		try {
			
			logger.info("Filtrar por [" + allParams.entrySet() + "]");
			
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			listDTO = this.ownerService.getByFilter(allParams);
			listDTO.setCode(SuccessConstants.GENERIC);
			listDTO.setMessage(message);
		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			listDTO = new ResponseListDTO(); 
			listDTO.setCode(ErrorConstans.GENERIC);
			listDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			listDTO = new ResponseListDTO();
			listDTO.setCode(ErrorConstans.GENERIC);
			listDTO.setMessage(message);
		}

		return new ResponseEntity<BaseResponseDTO>(listDTO, HttpStatus.OK);
	}

	/**
	 * Expone servicio de crear un propietario
	 * @param ownerDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> create(@RequestBody OwnerDTO ownerDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		
		String tokenJWTAuth = null;
		try {
			
			String tmpPassword = ownerDTO.getPassword();
			logger.info("ESTA ES LA CONTRASENA DE OWNER MIRA SI ESTA DECODE O CODE?==================================================================================="+tmpPassword );
			//ownerDTO = this.ownerService.create(ownerDTO);
			responseObjectDTO.setDatos(ownerService.create(ownerDTO));
			responseObjectDTO.setCode(Constants.CREAR);
			responseObjectDTO.setMessage(messageSource.getMessage(Constants.PREFIJO + Constants.CREAR, new Object[]{"Cliente"}, LocaleContextHolder.getLocale()));

			authenticator.authenticateOwner(ownerDTO.getEmail(), tmpPassword);
			final UserDetails userDetails = ownerService.loadUserByUsername(ownerDTO.getEmail());
			final String token = jwtTokenUtil.generateToken(userDetails);
			tokenJWTAuth = new StringBuffer().append(ConstantsSecurityConfig.CABECERA_AUTORIZACION_PREFIJO_BEARER).append(" ").append(token).toString();

		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		}
		
		return ResponseEntity.status(HttpStatus.OK).header(Constants.LLAVE_CABECERA_AUTORIZACION, tokenJWTAuth).body(responseObjectDTO);
		}
	
	/**
	 * Expone servicio de actualizar un propietario
	 * @param id Identificador del propietario
	 * @param bookDTO
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> update(@PathVariable("id") int id, @RequestBody OwnerDTO ownerDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			ownerDTO = this.ownerService.update(id, ownerDTO);
			
			responseObjectDTO.setData(ownerDTO);
			responseObjectDTO.setCode(SuccessConstants.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		}
		
		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
	}
	
	/**
	 * Exponse servicio de eliminar propietario
	 * @param id Identificador del propietario
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			this.ownerService.delete(id);
			baseResponseDTO.setCode(SuccessConstants.GENERIC);
			baseResponseDTO.setMessage(message);
		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			baseResponseDTO.setCode(ErrorConstans.GENERIC);
			baseResponseDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			baseResponseDTO.setCode(ErrorConstans.GENERIC);
			baseResponseDTO.setMessage(message);
		}
		
		return new ResponseEntity<BaseResponseDTO>(baseResponseDTO, HttpStatus.OK);
	}

}
