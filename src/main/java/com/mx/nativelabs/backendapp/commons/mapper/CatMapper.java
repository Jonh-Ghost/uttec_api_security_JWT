package com.mx.nativelabs.backendapp.commons.mapper;

import com.mx.nativelabs.backendapp.commons.model.cat.BaseCatDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.CatTypeBusinessDO;
import com.mx.nativelabs.backendapp.commons.model.entity.CatTypeMenuProductDO;
import com.mx.nativelabs.backendapp.commons.model.entity.StatusDO;

public class CatMapper {

	private CatMapper(){}

	/**
	 * Transfiere datos del cat&aacute;logo de tipo de negocio 
	 * a un base cat DTO
	 * @param catStatusDO
	 * @return
	 */
	public static BaseCatDTO toMap(StatusDO Status) {
		return new BaseCatDTO(Status.getIdTypeBusiness(), Status.getName(), Status.getDescription());
	}
	
	public static BaseCatDTO toMap(CatTypeBusinessDO catTypeBusinessDO ) {
		return new BaseCatDTO(catTypeBusinessDO.getIdTypeBusiness(), catTypeBusinessDO.getName(), catTypeBusinessDO.getDescription());
	}
	
	public static BaseCatDTO toMap(CatTypeMenuProductDO catTypeMenuProductDO ) {
		return new BaseCatDTO(catTypeMenuProductDO.getIdTypeMenuProduct(), catTypeMenuProductDO.getName(), catTypeMenuProductDO.getDescription());
	}
}
