package com.jrdevel.aboutus.core.common;

import java.util.List;

import com.jrdevel.aboutus.core.dto.GenericValueTextDTO;

public interface ListDAO extends GenericDAO<GenericValueTextDTO, Integer>{
	
	public List<GenericValueTextView> getCountryList();
	public List<GenericValueTextView> getCivilStatusList();
	public List<GenericValueTextView> getMemberTypeList();
	public List<GenericValueTextView> getContactTypeList();

}
