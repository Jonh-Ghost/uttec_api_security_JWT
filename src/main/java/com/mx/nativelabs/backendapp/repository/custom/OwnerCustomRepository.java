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
import com.mx.nativelabs.backendapp.commons.model.entity.OwnerDO;

@Repository
public class OwnerCustomRepository {

	private static final Logger logger = Logger.getLogger(OwnerCustomRepository.class);
	
	/**
	 * Query dinamico para consulta de propietarios
	 * @param filter
	 * @return
	 * @throws ServiceException
	 */
	public Specification<OwnerDO> queryWhereConditionFilter(Map<String, String> filter) throws ServiceException {
		return (root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			if (filter.get("email") != null) {
				logger.info("Binding email: " + filter.get("email"));
				predicates.add(cb.and(cb.like(root.get("email"), Utils.builderString("%", filter.get("email"), "%"))));				
			}
			
			if (filter.get("name") != null) {
				logger.info("Binding name: " + filter.get("name"));
				predicates.add(cb.and(cb.like(root.get("name"), Utils.builderString("%", filter.get("name"), "%"))));				
			}
			
			if (filter.get("lastName") != null) {
				logger.info("Binding lastName: " + filter.get("lastName"));
				predicates.add(cb.and(cb.like(root.get("lastName"), Utils.builderString("%", filter.get("lastName"), "%"))));				
			}
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
}
