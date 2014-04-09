package com.jrdevel.aboutus.core.common;

import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public abstract class AbstractGenericService<T> implements GenericService<T>{
	
	public ResultObject newResultObject(){
		return new ResultObject();
	}
	
	public ResultObject newResultObject(ListResult<?> listResult){
		ResultObject result = newResultObject();
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		return result;
	}
	
}
