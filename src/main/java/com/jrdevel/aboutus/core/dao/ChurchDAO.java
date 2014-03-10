package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Church;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ChurchDAO extends GenericDAO<Church, Integer>{
	
	public void setExtraFilters(Criteria criteria) {
		
	}

}
