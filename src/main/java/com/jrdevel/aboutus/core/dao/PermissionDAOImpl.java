package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Permission;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PermissionDAOImpl extends AbstractGenericDAO<Permission, Integer> implements PermissionDAO{
	
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

}
