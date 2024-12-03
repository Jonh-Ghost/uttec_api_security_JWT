package com.mx.nativelabs.backendapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.FileDO;

public interface FileRepository extends CrudRepository<FileDO, Integer>, JpaSpecificationExecutor<FileDO>{
	
	Optional<FileDO> findByProduct(int id);

}
