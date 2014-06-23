package com.jrdevel.aboutus.core.calendar;

import java.util.Date;
import java.util.List;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Event;

/**
 * @author Raphael Domingues
 *
 */
public interface EventDAO extends GenericDAO<Event, Integer>{
	
	public List<EventListView> callProcedure(Date start, Date end);
	
	public List<EventListView> getHomePageEvents();

}
