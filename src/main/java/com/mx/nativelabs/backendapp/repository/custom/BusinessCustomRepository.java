package com.mx.nativelabs.backendapp.repository.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.entity.UserDO;

@Repository
public class BusinessCustomRepository {

private static final Logger logger = Logger.getLogger(BusinessCustomRepository.class);
	
	/**
	 * Query dinamico para consulta de negocio
	 * @param filter
	 * @return
	 * @throws ServiceException
	 */
	public Specification<UserDO> queryWhereConditionFilter(Map<String, String> filter) throws ServiceException {
		return (root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			if (filter.get("name") != null) {
				logger.info("Binding email: " + filter.get("name"));
				predicates.add(cb.and(cb.like(root.get("name"), Utils.builderString("%", filter.get("name"), "%"))));				
			}
			
			if (filter.get("type") != null) {
				logger.info("Binding name: " + filter.get("type"));
				predicates.add(cb.and(cb.like(root.get("type"), Utils.builderString("%", filter.get("type"), "%"))));				
			}
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
