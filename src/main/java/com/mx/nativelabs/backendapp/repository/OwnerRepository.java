package com.mx.nativelabs.backendapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;

public interface OwnerRepository extends CrudRepository<OwnerDO, Integer>, JpaSpecificationExecutor<OwnerDO>{
	
	Optional<OwnerDO> findByIdOwner(int id);
	Optional<OwnerDO> findByEmail(String email);
	/**
	 * Consulta del cliente por contrase&ntilde;a
	 * @param contransena
	 * @return
	 */
	Optional<OwnerDO> findByPasswordAndEmail(String password,String email);
	

}
