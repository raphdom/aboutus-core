package com.jrdevel.aboutus.core.common;

import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public abstract class AbstractGenericService<T> implements GenericService<T>{
	
	public ResultObject newResultObject(){
		return new ResultObject();
	}
	
}
