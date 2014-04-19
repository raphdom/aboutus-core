package com.jrdevel.aboutus.core.person;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.to.ListResult;

public interface PersonDAO extends GenericDAO<Person, Integer>{
	
	public ListResult<PersonComboListView> findComboList();
	
}
