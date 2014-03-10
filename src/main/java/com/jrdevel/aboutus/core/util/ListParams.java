package com.jrdevel.aboutus.core.util;

import java.util.List;

/**
 * @author Raphael Domingues
 *
 */
public class ListParams {
	
	private int start;
	private int limit;
	private int page;
//	private String filter;
	private String sort;
	private List<Filter> filter;
	private List<Sort> sorters;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	@SuppressWarnings("unchecked")
	public void setSort(String sort) {
		//setSorters((List<Sort>) JsonUtils.getListObjectsFromJson(sort, Sort.class));
		this.sort = sort;
	}
//	public String getFilter() {
//		return filter;
//	}
//	@SuppressWarnings("unchecked")
//	public void setFilter(List<Filter> filter) {
//		//setFilters((List<Filter>) JsonUtils.getListObjectsFromJson(filter, Filter.class));
//		this.filter = filter;
//	}
	public List<Filter> getFilter() {
		return filter;
	}
	public void setFilter(List<Filter> filters) {
		this.filter = filters;
	}
	public List<Sort> getSorters() {
		return sorters;
	}
	public void setSorters(List<Sort> sorters) {
		this.sorters = sorters;
	}
	
}
