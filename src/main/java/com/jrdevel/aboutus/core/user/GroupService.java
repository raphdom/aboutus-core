package com.jrdevel.aboutus.core.user;

import java.util.List;

import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;


/**
 * @author Raphael Domingues
 *
 */
public interface GroupService{

	public ResultObject list(ListParams input);

	public ResultObject get(Group input);

	public ResultObject update(Group input);

	public ResultObject delete(List<Group> input);

}
