package com.jrdevel.aboutus.core.user;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.User;

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
