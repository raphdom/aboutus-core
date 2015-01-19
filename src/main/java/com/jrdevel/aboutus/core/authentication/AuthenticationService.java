package com.jrdevel.aboutus.core.authentication;

import net.aboutchurch.common.to.ResultObject;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jrdevel.aboutus.core.common.model.Register;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
public interface AuthenticationService extends UserDetailsService{
	
	public ResultObject login(User user);
	public ResultObject register(Register register);
	public void updateLogin(Integer id);
	public void logout();
	public ResultObject recoverPassword(String email);
	public ResultObject listPermissions();

}
