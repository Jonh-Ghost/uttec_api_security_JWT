package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mx.nativelabs.backendapp.commons.model.dto.BusinessDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.UserDO;

public class UserMapper {

	private UserMapper() {
	}

	public static BusinessDTO toGet(UserDO userDO) {
		BusinessDTO businessDTO = new BusinessDTO();
		businessDTO.setId(userDO.getIdBusiness());
		businessDTO.setName(userDO.getName());
		businessDTO.setTypeBusiness(CatMapper.toMap(userDO.getType()));
		businessDTO.setDateModification(userDO.getDateModification());
		businessDTO.setUserCreation(userDO.getUserCreation());
		businessDTO.setUserModification(userDO.getUserModification());
		businessDTO.setDateRegistration(userDO.getDateRegistration());
		businessDTO.setDateCreation(userDO.getDateCreation());
		return businessDTO;
	}

	public static List<BusinessDTO> toGet(List<UserDO> userDOs) {
		List<BusinessDTO> businessDTOs = new ArrayList<BusinessDTO>();
		for (UserDO userDO : userDOs) {
			businessDTOs.add(toGet(userDO));
		}
		return businessDTOs;
	}

	public static UserDO toCreate(BusinessDTO businessDTO) {
		UserDO userDO = new UserDO();
		userDO.setIdBusiness(businessDTO.getId());
		userDO.setName(businessDTO.getName());
		userDO.setDateModification(businessDTO.getDateModification());
		userDO.setUserCreation(businessDTO.getUserCreation());
		userDO.setUserModification(businessDTO.getUserModification());
		userDO.setDateRegistration(businessDTO.getDateRegistration());
		userDO.setDateCreation(businessDTO.getDateCreation());
		return userDO;
	}

	public static void toUpdate(BusinessDTO businessDTO, UserDO userDO) {
		userDO.setName(businessDTO.getName());
		userDO.setDateModification(businessDTO.getDateModification());
		userDO.setUserCreation(businessDTO.getUserCreation());
		userDO.setUserModification(businessDTO.getUserModification());
		userDO.setDateRegistration(businessDTO.getDateRegistration());
		userDO.setDateCreation(businessDTO.getDateCreation());
	}

}
