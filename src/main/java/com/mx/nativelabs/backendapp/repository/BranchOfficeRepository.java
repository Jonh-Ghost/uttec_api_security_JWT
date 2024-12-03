package com.mx.nativelabs.backendapp.repository;

import com.mx.nativelabs.backendapp.commons.model.entity.BranchOfficeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BranchOfficeRepository extends CrudRepository<BranchOfficeDO, Integer>, JpaSpecificationExecutor<BranchOfficeDO> {
	
	Optional<BranchOfficeDO> findByIdBranchOfficeAndActiveNot(int id, Integer active); 
	

}
