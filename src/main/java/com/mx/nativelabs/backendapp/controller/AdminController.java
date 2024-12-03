package com.mx.nativelabs.backendapp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.nativelabs.backendapp.config.utils.Authenticator;
import com.mx.nativelabs.backendapp.config.utils.JwtTokenUtil;
import com.mx.nativelabs.backendapp.service.AdministradorService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired 
	AdministradorService administradorService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	Authenticator authenticator;

//	/**
//	 * Expone servicio de consulta administrador por id
//	 * @param id Identificador del administrador
//	 * @return Respuesta con el detalle del administrador
//	 */
//	@ResponseBody
//	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponseDTO> getById(@PathVariable("id") int id){
//		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
//		try {
//			
//			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
//			AdminDTO adminDTO = this.administradorService.getById(id);
//			
//			responseObjectDTO.setData(adminDTO);
//			responseObjectDTO.setCode(SuccessConstants.GENERIC);
//			responseObjectDTO.setMessage(message);
//		} catch(ServiceException e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			responseObjectDTO.setCode(ErrorConstans.GENERIC);
//			responseObjectDTO.setMessage(message);
//		} catch (Exception e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			responseObjectDTO.setCode(ErrorConstans.GENERIC);
//			responseObjectDTO.setMessage(message);
//		}
//		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
//	}*/
//
//
//	/**
//	 * Exponse servicio de consulta de administrador por criterios de b&uacute;suqeda
//	 * @param allParams
//	 * @return
//	 */
//	@ResponseBody
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponseDTO> getByFilter(@RequestParam Map<String, String> allParams) {
//		ResponseListDTO listDTO = null;
//
//		try {
//			
//			logger.info("Filtrar por [" + allParams.entrySet() + "]");
//			
//			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
//			listDTO = this.administradorService.getByFilter(allParams);
//			listDTO.setCode(SuccessConstants.GENERIC);
//			listDTO.setMessage(message);
//		} catch (ServiceException e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			listDTO = new ResponseListDTO(); 
//			listDTO.setCode(ErrorConstans.GENERIC);
//			listDTO.setMessage(message);
//		} catch (Exception e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			listDTO = new ResponseListDTO();
//			listDTO.setCode(ErrorConstans.GENERIC);
//			listDTO.setMessage(message);
//		}
//
//		return new ResponseEntity<BaseResponseDTO>(listDTO, HttpStatus.OK);
//	}
///**
//	/**
//	 * Expone servicio de crear un administrador
//	 * @param adminDTO
//	 * @return
//	 */
//	@ResponseBody
//	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponseDTO> create(@RequestBody AdminDTO adminDTO) {
//		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
//		try {
//			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
//			adminDTO = this.administradorService.create(adminDTO);
//			
//			responseObjectDTO.setData(adminDTO);
//			responseObjectDTO.setCode(SuccessConstants.GENERIC);
//			responseObjectDTO.setMessage(message);
//		} catch (ServiceException e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			responseObjectDTO.setCode(ErrorConstans.GENERIC);
//			responseObjectDTO.setMessage(message);
//		} catch (Exception e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			responseObjectDTO.setCode(ErrorConstans.GENERIC);
//			responseObjectDTO.setMessage(message);
//		}
//		
//		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
//	}
//	
//	/**
//	 * Expone servicio de actualizar un administrador
//	 * @param id Identificador del administrador
//	 * @param bookDTO
//	 * @return
//	 */
//	@ResponseBody
//	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponseDTO> update(@PathVariable("id") int id, @RequestBody AdminDTO adminDTO) {
//		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
//		try {
//			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
//			adminDTO = this.administradorService.update(id, adminDTO);
//			
//			responseObjectDTO.setData(adminDTO);
//			responseObjectDTO.setCode(SuccessConstants.GENERIC);
//			responseObjectDTO.setMessage(message);
//		} catch (ServiceException e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			responseObjectDTO.setCode(ErrorConstans.GENERIC);
//			responseObjectDTO.setMessage(message);
//		} catch (Exception e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			responseObjectDTO.setCode(ErrorConstans.GENERIC);
//			responseObjectDTO.setMessage(message);
//		}
//		
//		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
//	}
//	
//	/**
//	 * Exponse servicio de eliminar administrador
//	 * @param id Identificador del administrador
//	 * @return
//	 */
//	@ResponseBody
//	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
//		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
//		try {
//			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
//			this.administradorService.delete(id);
//			baseResponseDTO.setCode(SuccessConstants.GENERIC);
//			baseResponseDTO.setMessage(message);
//		} catch (ServiceException e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			baseResponseDTO.setCode(ErrorConstans.GENERIC);
//			baseResponseDTO.setMessage(message);
//		} catch (Exception e) {
//			logger.error("", e);
//			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
//			baseResponseDTO.setCode(ErrorConstans.GENERIC);
//			baseResponseDTO.setMessage(message);
//		}
//		
//		return new ResponseEntity<BaseResponseDTO>(baseResponseDTO, HttpStatus.OK);
//	}

}
