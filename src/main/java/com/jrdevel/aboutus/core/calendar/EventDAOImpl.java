package com.jrdevel.aboutus.core.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Event;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class EventDAOImpl extends AbstractGenericDAO<Event, Integer> implements EventDAO{

	public void setExtraFilters(Criteria criteria) {
		
	}
	
	public List<EventListView> callProcedure(Date start, Date end){
		
		return callProcedure(start,end,null,UserAuthenticatedManager.getCurrentCustomer().getId(),null);
		
	}
	
	private List<EventListView> callProcedure(Date start, Date end, Integer count, Integer customerId, Integer calendarId){
		
		//Add one day to end date
		Calendar c = Calendar.getInstance(); 
		c.setTime(end); 
		c.add(Calendar.DATE, 1);
		Date rangeEnd = c.getTime();
		
		Query query = getSession().createSQLQuery(
				"CALL get_events_between(:rangeStart, :rangeEnd, :count, :customer, :calendar)")
				.setResultTransformer(Transformers.aliasToBean(EventListView.class))
				.setParameter("rangeStart", start)
				.setParameter("rangeEnd", rangeEnd)
				.setParameter("count", count)
				.setParameter("customer", customerId)
				.setParameter("calendar", calendarId);

		List<EventListView> result = query.list();
		
		return result;
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_EVENT.getKey();
	}

	public String getObjectTitle(Event entity) {
		return entity.getWhat();
	}

	public List<EventListView> getHomePageEvents() {
		
		Calendar start = Calendar.getInstance();
		
		Calendar end = Calendar.getInstance();
		end.add(Calendar.YEAR, 1);
		
		return callProcedure(start.getTime(),end.getTime(),4,null,1);
	}

}
