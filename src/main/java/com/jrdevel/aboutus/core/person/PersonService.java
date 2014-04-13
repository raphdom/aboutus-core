package com.jrdevel.aboutus.core.person;

import java.util.List;

import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;


/**
 * @author Raphael Domingues
 *
 */
public interface PersonService{

	public ResultObject list(ListParams input);

	public ResultObject update(Person data);

	public ResultObject get(Person input);

	public ResultObject delete(List<Person> input);
	
}
