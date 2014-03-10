package com.jrdevel.aboutus.core.authentication;

import com.jrdevel.aboutus.core.model.Register;
import com.jrdevel.aboutus.core.model.User;
import com.jrdevel.aboutus.core.util.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface AuthenticationService {
	
	public ResultObject login(User user);
	public ResultObject register(Register register);

}
