package com.mx.nativelabs.backendapp.repository.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.entity.BranchOfficeDO;

@Repository
public class BranchOfficeCustomRepository {
	
	private static final Logger logger = Logger.getLogger(BranchOfficeCustomRepository.class);
	
	/**
	 * Query dinamico para consulta de la sucursal
	 * @param filter 
	 * @return
	 * @throws ServiceException
	 */
	public Specification<BranchOfficeDO> queryWhereConditionFilter(Map<String, String> filter) throws ServiceException {
		return (root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			

			predicates.add(cb.and(cb.notEqual(root.get("active"),  Constants.ID_STATUS_BRANCHOFFICE_DELETED)));	
			
			
			if (filter.get("name") != null) {
				logger.info("Binding name: " + filter.get("name"));
				predicates.add(cb.and(cb.like(root.get("name"), Utils.builderString("%", filter.get("name"), "%"))));				
			}
			
			if (filter.get("nameManager") != null) {
				logger.info("Binding nameManager: " + filter.get("nameManager"));
				predicates.add(cb.and(cb.like(root.get("nameManager"), Utils.builderString("%", filter.get("nameManager"), "%"))));				
			}
			
			
			if (filter.get("phone") != null) {
				logger.info("Binding phone: " + filter.get("phone"));
				predicates.add(cb.and(cb.like(root.get("phone"), Utils.builderString("%", filter.get("phone"), "%"))));				
			}
			
			if (filter.get("active") != null) {
				logger.info("Binding active: " + filter.get("active"));
				predicates.add(cb.and(root.get("active").in(Utils.stringIds(filter.get("active")))));			
			}
			
			if (filter.get("idBusiness") != null) {
				logger.info("Binding business: " + filter.get("idBusiness"));
				predicates.add(cb.and( cb.equal( root.join("business").get("idBusiness") , filter.get("idBusiness")) ));
			}
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	

}
