package com.mx.nativelabs.backendapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.UserDO;

public interface BusinessRepository extends CrudRepository<UserDO, Integer>, JpaSpecificationExecutor<UserDO>{
	
	Optional<UserDO> findByIdBusiness(int id);
	
}