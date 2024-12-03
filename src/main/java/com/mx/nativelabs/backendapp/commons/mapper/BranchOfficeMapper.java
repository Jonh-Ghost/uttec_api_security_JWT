package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.dto.BranchOfficeDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.BranchOfficeDO;

public class BranchOfficeMapper {
	
	private BranchOfficeMapper() {}  
	
	public static BranchOfficeDTO toGet(BranchOfficeDO branchOfficeDO) {
		BranchOfficeDTO branchOfficeDTO = new BranchOfficeDTO();
		branchOfficeDTO.setId(branchOfficeDO.getIdBranchOffice());
		branchOfficeDTO.setName(branchOfficeDO.getName());
		branchOfficeDTO.setNameManager(branchOfficeDO.getNameManager());
		branchOfficeDTO.setAddress(branchOfficeDO.getAddress());
		branchOfficeDTO.setPhone(branchOfficeDO.getPhone());
		branchOfficeDTO.setEmail(branchOfficeDO.getEmail());
		branchOfficeDTO.setActive(branchOfficeDO.getActive());
		return branchOfficeDTO;
	}
	
	public static List<BranchOfficeDTO> toGet(List<BranchOfficeDO> branchOfficeDOs){
		List<BranchOfficeDTO> branchOfficeDTOs = new ArrayList<BranchOfficeDTO>();
		for(BranchOfficeDO branchOfficeDO : branchOfficeDOs) {
			branchOfficeDTOs.add(toGet(branchOfficeDO));			
		}
		return branchOfficeDTOs;
	}
	
	public static BranchOfficeDO toCreate(BranchOfficeDTO branchOfficeDTO) {
		BranchOfficeDO branchOfficeDO = new BranchOfficeDO();
		branchOfficeDO.setIdBranchOffice(branchOfficeDTO.getId());
		branchOfficeDO.setName(branchOfficeDTO.getName());
		branchOfficeDO.setNameManager(branchOfficeDTO.getNameManager());
		branchOfficeDO.setAddress(branchOfficeDTO.getAddress());
		branchOfficeDO.setPhone(branchOfficeDTO.getPhone());
		branchOfficeDO.setEmail(branchOfficeDTO.getEmail());
		branchOfficeDO.setActive(1);
		branchOfficeDO.setDateModification(Utils.getDate());
		branchOfficeDO.setUserCreation("email_01@gmail.com");
		branchOfficeDO.setUserModification("email_01@gmail.com");
		branchOfficeDO.setDateCreation(Utils.getDate());
		branchOfficeDO.setDateRegistration(Utils.getDate());
		return branchOfficeDO;
	}
	
	public static void toUpdate(BranchOfficeDTO branchOfficeDTO, BranchOfficeDO branchOfficeDO) {
		branchOfficeDO.setIdBranchOffice(branchOfficeDTO.getId());
		branchOfficeDO.setName(branchOfficeDTO.getName());
		branchOfficeDO.setNameManager(branchOfficeDTO.getNameManager());
		branchOfficeDO.setAddress(branchOfficeDTO.getAddress());
		branchOfficeDO.setEmail(branchOfficeDTO.getEmail());
		branchOfficeDO.setPhone(branchOfficeDTO.getPhone());
		branchOfficeDO.setActive(branchOfficeDTO.getActive());
		branchOfficeDO.setUserModification("email_01@gmail.com");
		branchOfficeDO.setDateModification(Utils.getDate());
	}

}
