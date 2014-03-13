package com.jrdevel.aboutus.core.common;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface GenericService<T> {
	
	public abstract ResultObject list(ListParams params);
	public abstract ResultObject update(T bean);
	public abstract ResultObject get(T bean);
	public abstract ResultObject delete(List<T> beans);

}
