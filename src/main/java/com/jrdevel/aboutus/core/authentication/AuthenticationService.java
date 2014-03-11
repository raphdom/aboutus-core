package com.jrdevel.aboutus.core.authentication;

import com.jrdevel.aboutus.core.common.model.Register;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface AuthenticationService {
	
	public ResultObject login(User user);
	public ResultObject register(Register register);

}
