package com.jrdevel.aboutus.core.audit;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface AuditService{
	
	public ResultObject list(ListParams params);

}
