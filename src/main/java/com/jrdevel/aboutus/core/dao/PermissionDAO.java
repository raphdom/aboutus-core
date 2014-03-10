package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Permission;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PermissionDAO extends GenericDAO<Permission, Integer>{
	
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

}
