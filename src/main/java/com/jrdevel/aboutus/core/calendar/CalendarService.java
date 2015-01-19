package com.jrdevel.aboutus.core.calendar;

import java.util.List;

import net.aboutchurch.common.dto.EventDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface CalendarService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(EventDTO categoryDTO);
	public ResultObject insert(EventDTO categoryDTO);
	public ResultObject save(EventDTO categoryDTO);
	public ResultObject delete(List<Integer> beans);
	

}
