package com.jrdevel.aboutus.core.common;

import java.util.List;

import net.aboutchurch.common.dto.GenericValueTextDTO;

/**
 * @author Raphael Domingues
 *
 */
public interface ListService {
	
	public List<GenericValueTextDTO> getCivilStatus();
	public List<GenericValueTextDTO> getCountry();
	public List<GenericValueTextDTO> getMemberType();
	public List<GenericValueTextDTO> getContactType();

}
