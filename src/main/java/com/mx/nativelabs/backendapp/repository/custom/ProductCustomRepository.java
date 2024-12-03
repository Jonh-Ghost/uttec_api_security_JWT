package com.mx.nativelabs.backendapp.repository.custom;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.constants.Constants;
import com.mx.nativelabs.backendapp.commons.exception.ServiceException;
import com.mx.nativelabs.backendapp.commons.model.entity.ProductDO;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class ProductCustomRepository {

    private static final Logger logger = Logger.getLogger(ProductCustomRepository.class);
    /**
     * Query dinamico para consulta de productos
     * @param filter
     * @return
     * @throws ServiceException
     */
    public Specification<ProductDO> queryWhereConditionFilter(Map<String, String> filter) throws ServiceException {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            
            predicates.add(cb.and(cb.notEqual(root.get("status"), Constants.ID_STATUS_PRODUCT_DELETED)));
            
            if (filter.get("idProduct") != null) {
                logger.info("Binding idProduct: " + filter.get("idProduct"));
                predicates.add(cb.and(cb.like(root.get("idProduct"), Utils.builderString("%", filter.get("idProduct"), "%"))));
            }
            if (filter.get("name") != null) {
                logger.info("Binding name: " + filter.get("name"));
                predicates.add(cb.and(cb.like(root.get("name"), Utils.builderString("%", filter.get("name"), "%"))));
            }

            if (filter.get("speciality") != null) {
                logger.info("Binding speciality: " + filter.get("speciality"));
                predicates.add(cb.and(cb.like(root.get("speciality"), Utils.builderString("%", filter.get("speciality"), "%"))));
            }

            if (filter.get("price") != null) {
                logger.info("Binding speciality: " + filter.get("price"));
                predicates.add(cb.and(root.get("price").in(Utils.stringPrices(filter.get("price")))));
            }
            
            if (filter.get("type") != null){
                logger.info("Binding status: " + filter.get("type"));
                predicates.add(cb.and(root.get("type").in(Utils.stringIds(filter.get("type")))));
            }

            if (filter.get("status") != null){
                logger.info("Binding status: " + filter.get("status"));
                predicates.add(cb.and(root.get("status").in(Utils.stringIds(filter.get("status")))));
            }
            
            if (filter.get("idBusiness") != null) {
				logger.info("Binding business: " + filter.get("idBusiness"));
				predicates.add(cb.and( cb.equal( root.join("business").get("idBusiness"), filter.get("idBusiness"))));
			}
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
