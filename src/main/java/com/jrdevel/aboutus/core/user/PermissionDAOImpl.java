package com.jrdevel.aboutus.core.user;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Permission;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PermissionDAOImpl extends AbstractGenericDAO<Permission, Integer> implements PermissionDAO{
	
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

	public String getObjectName() {
		return null;
	}

	public String getObjectTitle(Permission entity) {
		return null;
	}

}
