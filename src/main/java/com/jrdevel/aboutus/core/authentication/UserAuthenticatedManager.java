package com.jrdevel.aboutus.core.authentication;

import java.util.HashMap;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jrdevel.aboutus.core.common.model.Customer;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
public class UserAuthenticatedManager {
	
	private static UserDetailsAdapter getUserAdapter(){
		UserDetailsAdapter userAdapter = (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userAdapter;
	}
	
	public static User getCurrentUser(){
		return getUserAdapter().getUser();
	}
	
	public static Customer getCurrentCustomer(){
		return getCurrentUser().getCustomer();
	}
	
	public static HashMap<String, Integer> getPlanParams(){
		return getUserAdapter().getPlanParams();
	}

}
