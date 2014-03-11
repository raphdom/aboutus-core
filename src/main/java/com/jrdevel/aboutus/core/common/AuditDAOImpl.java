package com.jrdevel.aboutus.core.common;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.model.Audit;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class AuditDAOImpl extends AbstractGenericDAO<Audit, Integer> implements AuditDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}

}
