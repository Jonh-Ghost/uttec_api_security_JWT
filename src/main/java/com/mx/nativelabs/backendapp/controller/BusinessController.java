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
import com.mx.nativelabs.backendapp.commons.model.dto.BusinessDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseObjectDTO;
import com.mx.nativelabs.backendapp.service.impl.BusinessServiceImpl;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {

	private static final Logger logger = Logger.getLogger(BusinessController.class);

	@Autowired
	private BusinessServiceImpl businessService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Expone servicio de consulta negocio por id
	 * @param IdBusiness Identificador del negocio
	 * @return Respuesta con el detalle del negocio
	 */
	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getById(@PathVariable("id") int id){
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {

			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			BusinessDTO businessDTO = this.businessService.getById(id);

			responseObjectDTO.setData(businessDTO);
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
	 * Exponse servicio de consulta de negocio por criterios de b&uacute;suqeda
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
			listDTO = this.businessService.getByFilter(allParams);
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
	 * Expone servicio de crear un negocio
	 * @param BusinessDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> create(@RequestBody BusinessDTO businessDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			businessDTO = this.businessService.create(businessDTO);

			responseObjectDTO.setData(businessDTO);
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
	 * Expone servicio de actualizar un negocio
	 * @param id Identificador del propietario
	 * @param bookDTO
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> update(@PathVariable("id") int id, @RequestBody BusinessDTO businessDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			businessDTO = this.businessService.update(id, businessDTO);

			responseObjectDTO.setData(businessDTO);
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
	 * Exponse servicio de eliminar negocio por id
	 * @param id Identificador del negocio
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			this.businessService.delete(id);
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


	@ResponseBody
	@GetMapping(value = "/{nameBusiness}/{nameBranchOffice}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getByName(@PathVariable("nameBusiness") String nameBusiness, 
			@PathVariable("nameBranchOffice")	String nameBranchOffice) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();

		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null,
					LocaleContextHolder.getLocale());
			BusinessDTO businessDTO = this.businessService.getByNameBusiness(nameBusiness, nameBranchOffice);

			responseObjectDTO.setData(businessDTO);
			responseObjectDTO.setCode(SuccessConstants.GENERIC);
			responseObjectDTO.setMessage(message);

		} catch (ServiceException se) {
			logger.error("", se);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,
					LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);

		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,
					LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		}
		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
	}

}

