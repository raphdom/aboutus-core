package com.jrdevel.aboutus.core.user;

import java.util.List;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

import com.jrdevel.aboutus.core.common.model.Group;


/**
 * @author Raphael Domingues
 *
 */
public interface GroupService{

	public ResultObject update(Group input);

	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(GroupDTO userDTO);
	public ResultObject insert(GroupDTO userDTO);
	public ResultObject save(GroupDTO userDTO);
	public ResultObject delete(List<Integer> beans);

}
