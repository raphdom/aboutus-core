package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.File;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FileDAOImpl extends AbstractGenericDAO<File, Integer> implements FileDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

}
