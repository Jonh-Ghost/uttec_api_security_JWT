package com.mx.nativelabs.backendapp.controller;

import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.constants.SuccessConstants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.MenuDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseObjectDTO;
import com.mx.nativelabs.backendapp.service.impl.MenuServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/menu")
public class MenuController {

	private static final Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuServiceImpl menuService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Expone servicio de consulta de menú por id
	 *
	 * @param id Identificador del menú
	 * @return Respuesta con el detalle del menú
	 */

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getById(@PathVariable("id") int id) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null,
					LocaleContextHolder.getLocale());
			MenuDTO menuDTO = this.menuService.getById(id);

			responseObjectDTO.setData(menuDTO);
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

	/**
	 * Exponse servicio de consulta de menu por cirterios de b&uacute;suqeda
	 *
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
			listDTO = this.menuService.getByFilter(allParams);
			listDTO.setCode(SuccessConstants.GENERIC);
			listDTO.setMessage(message);
		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null, LocaleContextHolder.getLocale());
			listDTO = new ResponseListDTO();
			listDTO.setCode(ErrorConstans.GENERIC);
			listDTO.setMessage(message);
		}
		return new ResponseEntity<BaseResponseDTO>(listDTO, HttpStatus.OK);
	}

	/**
	 * Expone servicio de crear un menú
	 *
	 * @param menuDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> create(@RequestBody MenuDTO menuDTO) {

		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();

		try {

			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null,LocaleContextHolder.getLocale());
			menuDTO = this.menuService.create(menuDTO);

			responseObjectDTO.setData(menuDTO);
			responseObjectDTO.setCode(SuccessConstants.GENERIC);
			responseObjectDTO.setMessage(message);

		} catch (ServiceException e) {

			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);

		} catch (Exception e){

			logger.info("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message); 

		}

		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
	}

	/**
	 * Expone servicio de actualizar un menú
	 *
	 * @param id Identificador del menú
	 * @param menuDTO
	 * @return
	 */

	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> update(@PathVariable("id") int id, @RequestBody MenuDTO menuDTO) {
		ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null,LocaleContextHolder.getLocale());
			menuDTO = this.menuService.update(id, menuDTO);

			responseObjectDTO.setData(menuDTO);
			responseObjectDTO.setCode(SuccessConstants.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,LocaleContextHolder.getLocale());
			responseObjectDTO.setCode(ErrorConstans.GENERIC);
			responseObjectDTO.setMessage(message);
		}
		return new ResponseEntity<BaseResponseDTO>(responseObjectDTO, HttpStatus.OK);
	}

	/**
	 * Exponse servicio de eliminar menú
	 *
	 * @param id Identificador del menú
	 * @return
	 */

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null,
					LocaleContextHolder.getLocale());
			this.menuService.delete(id);

			baseResponseDTO.setCode(SuccessConstants.GENERIC);
			baseResponseDTO.setMessage(message);
		} catch (ServiceException e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,
					LocaleContextHolder.getLocale());
			baseResponseDTO.setCode(ErrorConstans.GENERIC);
			baseResponseDTO.setMessage(message);
		} catch (Exception e) {
			logger.error("", e);
			String message = this.messageSource.getMessage(ErrorConstans.PREFIX + ErrorConstans.GENERIC, null,
					LocaleContextHolder.getLocale());
			baseResponseDTO.setCode(ErrorConstans.GENERIC);
			baseResponseDTO.setMessage(message);
		}
		return new ResponseEntity<BaseResponseDTO>(baseResponseDTO, HttpStatus.OK);
	}
}
