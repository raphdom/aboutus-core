package com.jrdevel.aboutus.core.church;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;


/**
 * @author Raphael Domingues
 *
 */
public interface ChurchService{

	public ResultObject list(ListParams input);

}
