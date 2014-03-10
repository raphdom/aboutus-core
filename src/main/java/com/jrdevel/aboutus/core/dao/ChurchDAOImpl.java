package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Church;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ChurchDAOImpl extends AbstractGenericDAO<Church, Integer> implements ChurchDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}

}
