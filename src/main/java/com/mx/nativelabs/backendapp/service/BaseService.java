package com.mx.nativelabs.backendapp.service;

import java.util.Map;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.BusinessDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
/**
 * Template Base Service CRUD b&aacute;sico
 * @author Nativelabs21
 *
 * @param <BaseDTO>
 */
public interface BaseService<T extends BaseDTO> {

	/**
	 * M&eacute;todo gen&eacute;rico consulta por id
	 * @param id Identificador de la entidad de negocio
	 * @return Información detalle de la entidad de negocio
	 * @throws ServiceException Lanza error pensonalizado
	 */
	public T getById(int id) throws ServiceException;
	/**
	 * Invoca custom repository consultas y construye un query
	 * en base a los citerios de b&uacute;squeda 
	 * @param filterParams
	 * @return
	 * @throws ServiceException
	 */
	
	ResponseListDTO getByFilter(Map<String, String> filterParams)throws ServiceException;

	/**
	 * M&eacute;todo gen&eacute;rico para nuevos registros
	 * @param baseDTO
	 * @return Nueva entidad de negocio registrada
	 * @throws ServiceException Lanza error pensonalizado
	 */
	T create(T baseDTO) throws ServiceException;

	/**
	 * M&eacute;todo gen&eacute;rico para realizar actualizaciones
	 * @param id Identificador de la entidad de negocio
	 * @param userDTO
	 * @return Entidad de negocio actualizada
	 * @throws ServiceException Lanza error pensonalizado
	 */
	T update(int id, T baseDTO) throws ServiceException;

	/**
	 * M&eacute;todo gen&eacute;rico eliminar por id
	 * @param id Identificador de la entidad de negocio
	 * @throws ServiceException Lanza error pensonalizado
	 */
	void delete(int id) throws ServiceException;
	
}
