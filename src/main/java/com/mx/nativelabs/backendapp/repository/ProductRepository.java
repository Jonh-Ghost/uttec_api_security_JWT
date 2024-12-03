package com.mx.nativelabs.backendapp.repository;

import com.mx.nativelabs.backendapp.commons.model.entity.ProductDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductDO, Integer>, JpaSpecificationExecutor<ProductDO> {

    Optional<ProductDO> findByIdProductAndStatusNot(int id, Integer status);
}
