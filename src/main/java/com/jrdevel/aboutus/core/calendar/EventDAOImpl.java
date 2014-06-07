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
		
		//Add one day to end date
		Calendar c = Calendar.getInstance(); 
		c.setTime(end); 
		c.add(Calendar.DATE, 1);
		Date rangeEnd = c.getTime();
		
		Query query = getSession().createSQLQuery(
				"CALL get_events_between(:rangeStart, :rangeEnd, :count, :customer)")
				.setResultTransformer(Transformers.aliasToBean(EventListView.class))
				.setParameter("rangeStart", start)
				.setParameter("rangeEnd", rangeEnd)
				.setParameter("count", null)
				.setParameter("customer", UserAuthenticatedManager.getCurrentCustomer().getId());

		List<EventListView> result = query.list();
		
		return result;
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_EVENT.getKey();
	}

	public String getObjectTitle(Event entity) {
		return entity.getWhat();
	}

}
