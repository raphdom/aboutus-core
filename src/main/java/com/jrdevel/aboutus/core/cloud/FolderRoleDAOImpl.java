package com.jrdevel.aboutus.core.cloud;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.FolderRole;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FolderRoleDAOImpl extends AbstractGenericDAO<FolderRole, Integer> implements FolderRoleDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

}
