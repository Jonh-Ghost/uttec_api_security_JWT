package com.mx.nativelabs.backendapp.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.constants.SuccessConstants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.service.CatTypeMenuProductService;
import com.mx.nativelabs.backendapp.service.impl.CatTypeMenuProductServiceImpl;

@Controller
@RequestMapping(value = "/api/v1/cat-type-menu-product")
public class CatTypeMenuProductController {

	private static final Logger logger = Logger.getLogger(CatTypeMenuProductServiceImpl.class);

	@Autowired
	private CatTypeMenuProductService catTypeMenuProductService;

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> getByFilter(@RequestParam Map<String, String> allParams) {

		ResponseListDTO listDTO = null;

		try {
			logger.info("Filtrar por [" + allParams.entrySet() + "]");
			String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
			listDTO = this.catTypeMenuProductService.getByFilter(allParams);
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
}
