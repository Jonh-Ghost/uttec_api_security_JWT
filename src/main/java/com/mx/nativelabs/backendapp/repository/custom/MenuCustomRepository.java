package com.mx.nativelabs.backendapp.repository.custom;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.entity.MenuDO;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MenuCustomRepository {

	private static final org.apache.log4j.Logger logger = Logger.getLogger(MenuCustomRepository.class);

	/**
	 * Query dinamico para consulta de menu
	 * 
	 * @param filter
	 * @return
	 * @throws ServiceException
	 */
	public Specification<MenuDO> queryWhereConditionFilter(Map<String, String> filter) throws ServiceException {

		return (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			predicates.add(cb.and(cb.notEqual(root.get("status"), Constants.ID_STATUS_MENU_DELETED)));

			if (filter.get("status") != null) {
				logger.info("Binding status: " + filter.get("status"));
				predicates.add(cb.and(root.get("status").in(Utils.stringIds(filter.get("status")))));
			}

			if (filter.get("typeMenu") != null) {
				logger.info("Binding typeMenu: " + filter.get("typeMenu"));
				predicates.add(cb.and(root.get("typeMenu").in(Utils.stringIds(filter.get("typeMenu")))));
			}

			if (filter.get("idBranchOffice") != null) {
				logger.info("Binding BranchOffice: " + filter.get("idBranchOffice"));
				predicates.add(cb.and(cb.equal(root.join("branchOffice").get("idBranchOffice"), filter.get("idBranchOffice"))));
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
