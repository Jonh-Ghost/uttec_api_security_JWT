package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.dto.AdminDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.OwnerDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.AdminDO;
import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;


public class AdminMapper {
	
	private AdminMapper(){}
	
	public static AdminDTO toGet(AdminDO adminDO){
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(adminDO.getIdAdmin());
		adminDTO.setName(adminDO.getName());
		adminDTO.setLastName(adminDO.getLastName());
		adminDTO.setEmail(adminDO.getEmail());
		adminDTO.setPassword(adminDO.getPassword());
		adminDTO.setActive(adminDO.isActive());
		adminDTO.setDateModification(adminDO.getDateModification());
		adminDTO.setUserCreation(adminDO.getUserCreation());
		adminDTO.setUserModification(adminDO.getUserModification());
		adminDTO.setDateCreation(adminDO.getDateCreation());
		return adminDTO;		
	}
	
	public static List<AdminDTO> toGet(List<AdminDO> adminDOs) {
		List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
		for (AdminDO adminDO : adminDOs) {
			adminDTOs.add(toGet(adminDO));
		}
		return adminDTOs;
	}
		
	public static AdminDO toCreate(AdminDTO adminDTO){
		AdminDO adminDO = new AdminDO();
		adminDO.setIdAdmin(adminDTO.getId());
		adminDO.setName(adminDTO.getName());
		adminDO.setLastName(adminDTO.getLastName());
		adminDO.setEmail(adminDTO.getEmail());
		adminDO.setPassword(adminDTO.getPassword());
		adminDO.setActive(adminDTO.isActive());
		adminDO.setUserCreation("email_01@gmail.com");
		adminDO.setDateCreation(Utils.getDate());
		return adminDO;			
	}
	
	public static void toUpdate(AdminDTO adminDTO, AdminDO adminDO){
		adminDO.setIdAdmin(adminDTO.getId());
		adminDO.setName(adminDTO.getName());
		adminDO.setLastName(adminDTO.getLastName());
		adminDO.setEmail(adminDTO.getEmail());
		adminDO.setPassword(adminDTO.getPassword());
		adminDO.setActive(adminDTO.isActive());
		adminDO.setDateModification(Utils.getDate());
		adminDO.setUserModification("email_01@gmail.com");
	}
	
	/**
	 * Transfiere los datos de adminDO a adminDTO
	 * @param administradorDO
	 * @return
	 */
	public static AdminDTO mapper(AdminDO adminDO) {

		AdminDTO adminDTO = new AdminDTO();

		adminDTO.setId(adminDO.getIdAdmin());
		adminDTO.setName(adminDO.getName());
		adminDTO.setEmail(adminDO.getEmail());
		adminDTO.setLastName(adminDO.getLastName());

		return adminDTO;
	}
	
	/**
	 * Transfiere los datos de un DTO a un DO de owner
	 * @param clienteDOs
	 * @return
	 */
	public static List<AdminDTO> mapper(Page<AdminDO> AdminDOs) {
		List<AdminDTO> adminDTOs = new ArrayList<>();

		AdminDOs.forEach(adminDO -> {
			adminDTOs.add(mapper(adminDO));
		});

		return adminDTOs;
	}

}
