package com.jrdevel.aboutus.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.util.ListParams;
import com.jrdevel.aboutus.core.util.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public abstract class GenericService<T> {
	
	public ResultObject newResultObject(){
		return new ResultObject();
	}
	
	@Transactional
	public abstract ResultObject list(ListParams params);
	@Transactional
	public abstract ResultObject update(T bean);
	@Transactional
	public abstract ResultObject get(T bean);
	@Transactional
	public abstract ResultObject delete(List<T> beans);

}
