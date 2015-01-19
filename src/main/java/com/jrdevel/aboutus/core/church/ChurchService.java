package com.jrdevel.aboutus.core.church;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;


/**
 * @author Raphael Domingues
 *
 */
public interface ChurchService{

	public ResultObject list(ListParams input);

}
