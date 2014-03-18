package com.jrdevel.aboutus.core.authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jrdevel.aboutus.core.common.model.Customer;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
public class UserAuthenticatedManager {
	
	public static User getCurrentUser(){
		UserDetailsAdapter user = (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUser();
	}
	
	public static Customer getCurrentCustomer(){
		return getCurrentUser().getCustomer();
	}

}
