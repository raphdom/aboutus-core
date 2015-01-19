package com.jrdevel.aboutus.core.common;

import java.util.List;

import net.aboutchurch.common.dto.GenericValueTextDTO;

public interface ListDAO extends GenericDAO<GenericValueTextDTO, Integer>{
	
	public List<GenericValueTextView> getCountryList();
	public List<GenericValueTextView> getCivilStatusList();
	public List<GenericValueTextView> getMemberTypeList();
	public List<GenericValueTextView> getContactTypeList();

}
