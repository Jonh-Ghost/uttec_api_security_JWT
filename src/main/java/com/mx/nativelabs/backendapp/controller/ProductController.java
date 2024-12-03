package com.mx.nativelabs.backendapp.controller;

import com.mx.nativelabs.backendapp.commons.constants.ErrorConstans;
import com.mx.nativelabs.backendapp.commons.constants.SuccessConstants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.ProductDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseResponseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseObjectDTO;
import com.mx.nativelabs.backendapp.service.impl.ProductServiceImpl;

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

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private MessageSource messageSource;

    /**
     * Expone servicio de consulta de productos por id
     *
     * @param id Identificador del producto
     * @return Respuesta con el detalle del producto
     */
    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDTO> getById(@PathVariable("id") int id) {
        ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
        try {
            String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
            ProductDTO productDTO = this.productService.getById(id);

            responseObjectDTO.setData(productDTO);
            responseObjectDTO.setCode(SuccessConstants.GENERIC);
            responseObjectDTO.setMessage(message);
        } catch (ServiceException se) {
            logger.error("", se);
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
     * Exponse servicio de consulta de productos por cirterios de b&uacute;suqeda
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
            listDTO = this.productService.getByFilter(allParams);
            listDTO.setCode(SuccessConstants.GENERIC);
            listDTO.setMessage(message);

        } catch (ServiceException se) {
            logger.error("", se);
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
     * Expone servicio de crear un usuario
     *
     * @param productDTO
     * @return
     */
    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDTO> create(@RequestBody ProductDTO productDTO) {
        ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
        try {
            String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
            productDTO = this.productService.create(productDTO);

            responseObjectDTO.setData(productDTO);
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
     * Expone servicio de actualizar un propietario
     *
     * @param id         Identificador del propietario
     * @param productDTO
     * @return
     */

    @ResponseBody
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDTO> update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO) {
        ResponseObjectDTO responseObjectDTO = new ResponseObjectDTO();
        try {
            String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
            productDTO = this.productService.update(id, productDTO);

            responseObjectDTO.setData(productDTO);
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
     *
     * @param id Identificador del propietario
     * @return
     */

    @ResponseBody
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDTO> delete(@PathVariable("id") int id) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        try {
            String message = this.messageSource.getMessage(SuccessConstants.PREFIX + SuccessConstants.GENERIC, null, LocaleContextHolder.getLocale());
            this.productService.delete(id);
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
/**
 * {
 *     "url": "Imagen3.png",
 *     "code": "32EEF12",
 *     "active": 0,
 *     "saucer": 0,
 *     "business": 1,
 *     "modificationDate": "2022-03-10",
 *     "creationUser": "Edwin_EASD",
 *     "modificationUser": "Edwin_EASDA",
 *     "creationDate": "2021-11-11",
 *     "registrationDate": "2022-12-12"
 * }
 * */