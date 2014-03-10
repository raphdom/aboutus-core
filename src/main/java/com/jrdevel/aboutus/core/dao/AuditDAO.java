package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Audit;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class AuditDAO extends GenericDAO<Audit, Integer>{
	
	public void setExtraFilters(Criteria criteria) {
		
	}

}
