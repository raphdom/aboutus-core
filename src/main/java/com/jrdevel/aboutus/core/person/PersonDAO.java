package com.jrdevel.aboutus.core.person;

import net.aboutchurch.common.to.ListResult;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.GenericIdTextView;
import com.jrdevel.aboutus.core.common.model.Person;

public interface PersonDAO extends GenericDAO<Person, Integer>{
	
	public ListResult<GenericIdTextView> findComboList();
	
}
