package com.mx.nativelabs.backendapp.service.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.MenuProductMapper;
import com.mx.nativelabs.backendapp.commons.model.dto.DocumentDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.MenuProductDO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.repository.MenuProductRepository;
import com.mx.nativelabs.backendapp.service.MenuProductService;

@Service
public class MenuProductServiceImpl implements MenuProductService{

	
	private static final Logger logger = Logger.getLogger(MenuProductServiceImpl.class);
	
	@Autowired
	private MenuProductRepository menuProductRepository;

	@Override
	public DocumentDTO getById(int id) throws ServiceException {
		

		return null;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {

		
		return null;
	}

	@Override
	public DocumentDTO create(DocumentDTO menuProductDTO) throws ServiceException {
		
		logger.info("Crear nuevo registro menu-producto: " + menuProductDTO);
		
		try {
			
			MenuProductDO menuProductDO = MenuProductMapper.toCreate(menuProductDTO);
			
			menuProductDTO.setId(menuProductDO.getIdMenuProduct());
			menuProductDO.setIdMenuProduct(menuProductDTO.getId());
			menuProductRepository.save(menuProductDO);

		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}
		return menuProductDTO;
	}

	@Override
	public DocumentDTO update(int id, DocumentDTO menuProductDTO) throws ServiceException {
		
		return null;
	}

	@Override
	public void delete(int id) throws ServiceException {
		
		logger.info("Eliminar menú con id: " + id);
		try {
			Optional<MenuProductDO> menuProductDO = menuProductRepository.findByIdMenu(id);
			if (!menuProductDO.isPresent()) {
				logger.info("Obj db es null");
				throw new ServiceException("El menú no existe");
			}
			menuProductRepository.delete(menuProductDO.get());
		} catch (ServiceException se) {
			logger.error("Exception: ", se);
			throw se;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ServiceException("Error generico: ");
		}

	}
		

}
