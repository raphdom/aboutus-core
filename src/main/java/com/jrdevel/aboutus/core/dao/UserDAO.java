package com.jrdevel.aboutus.core.dao;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.model.User;

/**
 * @author Raphael Domingues
 *
 */
public interface UserDAO extends GenericDAO<User, Integer>{
	
	public User getUserByEmail(String email);
	public boolean existEmailRegistered(String email);
	public User getUserById(int id);
	public User getUserSimpleById(int id);

}
