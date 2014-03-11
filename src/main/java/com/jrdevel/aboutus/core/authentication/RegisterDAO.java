package com.jrdevel.aboutus.core.authentication;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Register;
import com.jrdevel.aboutus.core.common.model.User;

public interface RegisterDAO extends GenericDAO<Register, Integer>{
	
	public boolean existEmailRegistered(String email);
	public Register getRegisterByUser(User user);

}
