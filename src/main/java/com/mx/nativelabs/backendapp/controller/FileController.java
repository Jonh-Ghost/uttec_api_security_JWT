package com.mx.nativelabs.backendapp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.nativelabs.backendapp.service.impl.StorageServiceImpl;


import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.constants.SuccessConstants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    private static final Logger logger = Logger.getLogger(FileController.class);

	@Autowired
	private StorageServiceImpl StorageService;
	
	@Autowired
    private MessageSource messageSource;
	
	 @ResponseBody
	 @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
		 BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
			try {
				String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
				this.StorageService.delete(id);
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
