package com.mx.nativelabs.backendapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.StatusDO;

public interface CatStatusRepository extends CrudRepository<StatusDO, Integer> {

}
