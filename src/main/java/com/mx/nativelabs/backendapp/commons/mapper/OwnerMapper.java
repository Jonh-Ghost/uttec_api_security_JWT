package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;
import com.mx.nativelabs.backendapp.config.utils.Utils;


public class OwnerMapper {

	private OwnerMapper() {
	}

	public static OwnerDTO toGet(OwnerDO ownerDO) {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(ownerDO.getIdOwner());
		ownerDTO.setName(ownerDO.getName());
		ownerDTO.setLastName(ownerDO.getLastName());
		ownerDTO.setEmail(ownerDO.getEmail());
		ownerDTO.setPassword(ownerDO.getPassword());
		ownerDTO.setActive(ownerDO.getActive());
		ownerDTO.setDateModification(ownerDO.getDateModification());
		ownerDTO.setUserCreation(ownerDO.getUserCreation());
		ownerDTO.setUserModification(ownerDO.getUserModification());
		ownerDTO.setDateRegistration(ownerDO.getDateRegistration());
		ownerDTO.setDateCreation(ownerDO.getDateCreation());
		return ownerDTO;
	}

	public static List<OwnerDTO> toGet(List<OwnerDO> ownerDOs) {
		List<OwnerDTO> ownerDTOs = new ArrayList<OwnerDTO>();
		for (OwnerDO ownerDO : ownerDOs) {
			ownerDTOs.add(toGet(ownerDO));
		}
		return ownerDTOs;
	}

	public static OwnerDO toCreate(OwnerDTO ownerDTO) {
		OwnerDO ownerDO = new OwnerDO();
		ownerDO.setIdOwner(ownerDTO.getId());
		ownerDO.setName(ownerDTO.getName());
		ownerDO.setLastName(ownerDTO.getLastName());
		ownerDO.setEmail(ownerDTO.getEmail());
		ownerDO.setPassword(ownerDTO.getPassword());
		ownerDO.setActive(ownerDTO.getActive());
		ownerDO.setUserCreation(ownerDTO.getUserCreation());
		ownerDO.setDateRegistration(Utils.getDayHourCurrentMexico());
		ownerDO.setDateCreation(Utils.getDayHourCurrentMexico());
		return ownerDO;
	}

	public static void toUpdate(OwnerDTO ownerDTO, OwnerDO ownerDO) {
		ownerDO.setName(ownerDTO.getName());
		ownerDO.setLastName(ownerDTO.getLastName());
		ownerDO.setEmail(ownerDTO.getEmail());
		ownerDO.setPassword(ownerDTO.getPassword());
		ownerDO.setActive(ownerDTO.getActive());
		ownerDO.setDateModification(Utils.getDayHourCurrentMexico());
		ownerDO.setUserModification(ownerDTO.getUserModification());
	}
	
	/*
	 * Transfiere los datos de OwnerDO a OwnerDTO
	 * @param ownerDO
	 * @return 
	 */
	
	public static OwnerDTO mapper(OwnerDO ownerDO) {
		OwnerDTO ownerDTO = new OwnerDTO();
		
		ownerDTO.setId(ownerDO.getIdOwner());
		ownerDTO.setName(ownerDO.getName());
		ownerDTO.setEmail(ownerDO.getEmail());	
		
		return ownerDTO;
	}
	
	/**
	 * Transfiere los datos de un DTO a un DO de owner
	 * @param clienteDOs
	 * @return
	 */
	public static List<OwnerDTO> mapper(Page<OwnerDO> OwnerDOs) {
		List<OwnerDTO> ownerDTOs = new ArrayList<>();

		OwnerDOs.forEach(ownerDO -> {
			ownerDTOs.add(mapper(ownerDO));
		});

		return ownerDTOs;
	}
}
