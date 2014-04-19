package com.jrdevel.aboutus.core.person;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.dto.PersonDTO;


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
