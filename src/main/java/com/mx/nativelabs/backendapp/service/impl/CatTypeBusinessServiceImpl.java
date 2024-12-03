package com.mx.nativelabs.backendapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.mapper.CatMapper;
import com.mx.nativelabs.backendapp.commons.model.cat.BaseCatDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.CatTypeBusinessDO;
import com.mx.nativelabs.backendapp.commons.model.response.ResponseListDTO;
import com.mx.nativelabs.backendapp.repository.CatTypeBusinessRepository;
import com.mx.nativelabs.backendapp.service.CatTypeBusinessService;

@Service
public class CatTypeBusinessServiceImpl implements CatTypeBusinessService {

	private static final Logger logger = Logger.getLogger(CatTypeBusinessServiceImpl.class);

	@Autowired
	private CatTypeBusinessRepository catStatusRepository;

	@Override
	public BaseCatDTO getById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseListDTO getByFilter(Map<String, String> filterParams) throws ServiceException {
		ResponseListDTO response = new ResponseListDTO();
		try {
			Iterable<CatTypeBusinessDO> typesBusiness = catStatusRepository.findAll();
			List<BaseCatDTO> list = new ArrayList<BaseCatDTO>();
			//
			if (typesBusiness != null) {
				typesBusiness.forEach(typeBusiness -> {
					list.add(CatMapper.toMap(typeBusiness));
				});
			}
			response.setData(list);
		} catch (Exception e) {
			logger.error("", e);
		}

		return response;
	}

	@Override
	public BaseCatDTO create(BaseCatDTO baseDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseCatDTO update(int id, BaseCatDTO baseDTO) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
