package com.mx.nativelabs.backendapp.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mx.nativelabs.backendapp.commons.model.entity.HistoricoQrDO;


public interface HistoricoQrRepository extends CrudRepository<HistoricoQrDO, Integer>, JpaSpecificationExecutor<HistoricoQrDO> {	

	Page<HistoricoQrDO> findTop5ByBranchOfficeIdBranchOffice(int idBranchOffice, Pageable pageable);
	
	HistoricoQrDO findByBranchOfficeIdBranchOfficeAndStatus(int idBranchOffice, boolean status);
	
	HistoricoQrDO findByUrlQr(String valueQr);

	List<HistoricoQrDO> findByBranchOfficeIdBranchOffice(int id);

}
