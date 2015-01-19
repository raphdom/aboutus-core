package com.jrdevel.aboutus.core.person;

import java.util.List;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;


/**
 * @author Raphael Domingues
 *
 */
public interface PersonService{

	public ResultObject list(ListParams params);
	public ResultObject listNames();
	public ResultObject get(Integer id);
	public ResultObject update(PersonDTO personDTO);
	public ResultObject insert(PersonDTO personDTO);
	public ResultObject save(PersonDTO personDTO);
	public ResultObject delete(List<Integer> beans);
	
}
