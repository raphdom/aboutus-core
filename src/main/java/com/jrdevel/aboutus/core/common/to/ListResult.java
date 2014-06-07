package com.jrdevel.aboutus.core.common.to;

import java.util.List;


/**
 * @author Raphael Domingues
 *
 */
public class ListResult<T>{
	
	private List<T> data;
	private long total;
	
	public ListResult(List<T> data){
		this.data=data;
		this.total=data.size();
	}
	
	public ListResult(List<T> data, long total){
		this.data=data;
		this.total=total;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

}
