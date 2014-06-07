package com.jrdevel.aboutus.core.audit;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Audit;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class AuditDAOImpl extends AbstractGenericDAO<Audit, Integer> implements AuditDAO{
	

	public void setExtraFilters(Criteria criteria) {
		
	}

	public String getObjectTitle(Audit entity) {
		return null;
	}

	public String getObjectName() {
		return null;
	}

}
