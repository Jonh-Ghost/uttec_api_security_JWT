package com.mx.nativelabs.backendapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.MenuProductDO;

public interface MenuProductRepository extends CrudRepository<MenuProductDO, Integer>, JpaSpecificationExecutor<MenuProductDO>{
	
	Optional<MenuProductDO> findByIdMenu(Integer id);
	Optional<MenuProductDO> findByIdProduct(Integer id);

}
