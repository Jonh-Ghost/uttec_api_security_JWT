package com.mx.nativelabs.backendapp.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.constants.SuccessConstants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.BranchOfficeDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseObjectDTO;
import com.mx.nativelabs.backendapp.service.impl.BranchOfficeServiceImpl;

@RestController
@RequestMapping("/api/v1/branchoffice")
public class BranchOfficeController {
	
	private static final Logger logger = Logger.getLogger(BranchOfficeController.class);
	
	@Autowired
	private BranchOfficeServiceImpl branchOfficeService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Expone servicio de consulta sucursal por id
	 * @param IdBranchoffice Identificador de la sucursal 
	 * @return Respuesta con el detalle de la sucursal
	 */ 
	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getById(@PathVariable("id") int id){
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			BranchOfficeDTO branchOfficeDTO = this.branchOfficeService.getById(id);
			
			responseObjectDTO.setData(branchOfficeDTO);
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
	 * Exponse servicio de consulta de la sucursal por criterios de b&uacute;suqeda
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
			listDTO = this.branchOfficeService.getByFilter(allParams);
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
	 * Expone servicio de crear una sucursal
	 * @param branchOfficeDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> create(@RequestBody BranchOfficeDTO branchOfficeDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			branchOfficeDTO = this.branchOfficeService.create(branchOfficeDTO);
			
			responseObjectDTO.setData(branchOfficeDTO);
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
	 * Expone servicio de actualizar una sucursal
	 * @param id Identificador de la sucursal
	 * @param branchOfficeDTO
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> update(@PathVariable("id") int id, @RequestBody BranchOfficeDTO branchOfficeDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			logger.info("Inicio de Try en el servicio actualizar" + branchOfficeDTO.toString());
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			branchOfficeDTO = this.branchOfficeService.update(id, branchOfficeDTO);
			
			responseObjectDTO.setData(branchOfficeDTO);
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
	 * Exponse servicio de eliminar sucursal
	 * @param id Identificador de al sucursal
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			this.branchOfficeService.delete(id);
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
