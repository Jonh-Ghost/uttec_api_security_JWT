package com.mx.nativelabs.backendapp.service;

import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.dto.BusinessDTO;

public interface BusinessService extends BaseService<BusinessDTO> {

	public BusinessDTO getByNameBusiness(String nameBusiness, String nameBranchOffice) throws ServiceException;
	
}
