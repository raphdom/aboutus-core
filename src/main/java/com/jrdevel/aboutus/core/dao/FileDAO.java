package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.File;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FileDAO extends GenericDAO<File, Integer>{
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

}
