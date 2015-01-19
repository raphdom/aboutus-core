package com.jrdevel.aboutus.core.audit;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface AuditService{
	
	public ResultObject list(ListParams params);

}
