package com.jrdevel.aboutus.core.common;

import java.util.List;

import com.jrdevel.aboutus.core.util.ListParams;
import com.jrdevel.aboutus.core.util.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public abstract class AbstractGenericService<T> implements GenericService<T>{
	
	public ResultObject newResultObject(){
		return new ResultObject();
	}
	
	public abstract ResultObject list(ListParams params);
	public abstract ResultObject update(T bean);
	public abstract ResultObject get(T bean);
	public abstract ResultObject delete(List<T> beans);

}
