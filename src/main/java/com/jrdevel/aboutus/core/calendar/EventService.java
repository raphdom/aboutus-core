package com.jrdevel.aboutus.core.calendar;

import java.util.Date;
import java.util.List;

import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface EventService{
	
	public ResultObject list(Date start, Date end);
	public ResultObject get(Integer id);
	public ResultObject update(EventDTO categoryDTO);
	public ResultObject insert(EventDTO categoryDTO);
	public ResultObject save(EventDTO categoryDTO);
	public ResultObject delete(Integer eventId);
	
	public ResultObject listHomePage();
	

}
