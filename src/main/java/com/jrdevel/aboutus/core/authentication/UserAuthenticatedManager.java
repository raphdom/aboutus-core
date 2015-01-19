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
		
		UserDetailsAdapter userAdapter = null;
		
		if (SecurityContextHolder.getContext().getAuthentication() != null &&
				SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
				!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			userAdapter = (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return userAdapter;
	}
	
	public static User getCurrentUser(){
		if (getUserAdapter()!= null){
			return getUserAdapter().getUser();
		}else{
			return null;
		}
	}
	
	public static Customer getCurrentCustomer(){
		if (getCurrentUser()!=null){
			return getCurrentUser().getCustomer();
		}
		return null;
	}
	
	public static HashMap<String, Integer> getPlanParams(){
		return getUserAdapter().getPlanParams();
	}
	

}
