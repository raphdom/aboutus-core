package com.jrdevel.aboutus.core.person;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;


/**
 * @author Raphael Domingues
 *
 */
public interface PersonService{

	public ResultObject list(ListParams params);
	public ResultObject listNames();
	public ResultObject get(Integer id);
	public ResultObject update(PersonDTO personDTO);
	@PreAuthorize("hasRole('ROLE_INSERT_PEOPLE2')")
	public ResultObject insert(PersonDTO personDTO);
	public ResultObject save(PersonDTO personDTO);
	public ResultObject delete(List<Integer> beans);
	
}
