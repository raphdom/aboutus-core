package com.jrdevel.aboutus.core.user;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface UserService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(UserDTO userDTO);
	public ResultObject insert(UserDTO userDTO);
	public ResultObject save(UserDTO userDTO);
	public ResultObject delete(List<Integer> beans);
	public ResultObject getCurrentProfile();
	public ResultObject updateProfile(ProfileDTO profileDTO);
	public ResultObject changePassword(String passActual, String passNew);

}
