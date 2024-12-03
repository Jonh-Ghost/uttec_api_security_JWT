package com.mx.nativelabs.backendapp.repository;

import com.mx.nativelabs.backendapp.commons.model.entity.MenuDO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<MenuDO, Integer>, JpaSpecificationExecutor<MenuDO> {

	Optional<MenuDO> findByIdMenuAndStatusNot(Integer id, Integer status);
	
}
