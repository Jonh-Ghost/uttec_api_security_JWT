package com.mx.nativelabs.backendapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.AdminDO;

public interface AdminRepository extends CrudRepository<AdminDO, Integer>, JpaSpecificationExecutor<AdminDO>{
	Optional<AdminDO> findByEmail(String email);
	Optional<AdminDO> findById(int id);
	Optional<AdminDO> findByPasswordAndEmail(String password,String email);

}
