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
	public ResultObject update(UserView bean);
	public ResultObject insert(UserView bean);
	public ResultObject save(UserView bean);
	public ResultObject delete(List<Integer> beans);
	

}
