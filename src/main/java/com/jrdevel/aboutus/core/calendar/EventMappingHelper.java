package com.jrdevel.aboutus.core.calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.model.Event;
import com.jrdevel.aboutus.core.common.model.EventRecurrence;
import com.jrdevel.aboutus.core.common.model.File;

/**
 * @author Raphael Domingues
 *
 */
public class EventMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static EventListDTO viewToDTO(EventListView view){
		EventListDTO dto = new EventListDTO();
		dto.setEid(view.getEventId());
		dto.setCid(view.getCid());
		if (view.getStartsAt()!=null){
			dto.setStart(view.getStartsAt());
			dto.setEnd(view.getEndsAt());
			dto.setAd(false);
		}else{
			dto.setStart(getDateWithTimeMidnight(view.getStartsOn()));
			dto.setEnd(getDateWithTimeMidnight(view.getEndsOn()));
			dto.setAd(true);
		}
		dto.setTitle(view.getWhat());
		dto.setThumbId(view.getThbId());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<EventListDTO> listViewTolistDTO(List<EventListView> views){
		List<EventListDTO> dtos = new ArrayList<EventListDTO>();
		int i = 1;
		for(EventListView view : views){
			EventListDTO dto = viewToDTO(view);
			dto.setId(i++);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public static EventDTO beanToDTO(Event bean){
		
		EventDTO dto = new EventDTO();
		dto.setId(bean.getId());
		dto.setEid(bean.getId());
		dto.setCid(bean.getCalendarId());
		if (bean.getCategory()!=null){
			dto.setCatId(bean.getCategory().getId());
		}
		if (bean.getFile()!=null){
			dto.setThbId(bean.getFile().getId());
		}
		dto.setPublished(bean.isPublished());
		dto.setTitle(bean.getWhat());
		dto.setLoc(bean.getLocation());
		if (bean.getStartsAt()!= null){
			dto.setStart(bean.getStartsAt());
			dto.setEnd(bean.getEndsAt());
		}else{
			dto.setStart(getDateWithTimeMidnight(bean.getStartsOn()));
			dto.setEnd(getDateWithTimeMidnight(bean.getEndsOn()));
			dto.setAd(true);
		}
		
		dto.setFrequency(bean.getFrequency());
		dto.setSeparation(bean.getSeparation());
		
		if (bean.getEventRecurrences()!=null && bean.getEventRecurrences().size()>0){
			Integer[] weekDays = new Integer[bean.getEventRecurrences().size()];
			int i = 0;
			for(EventRecurrence recurrence : bean.getEventRecurrences()){
				weekDays[i] = recurrence.getDay();
				i++;
			}
			Arrays.sort(weekDays);
			dto.setWeekDays(weekDays);
		}
		
		
		return dto;
	}
	
	public static Event DTOToBean(EventDTO dto){
		Event bean = new Event();
		return DTOToBean(dto, bean);
	}
	
	public static Event DTOToBean(EventDTO dto, Event bean){
		if (StringUtils.isEmpty(dto.getFrequency())){
			bean.setFrequency("once");
		}else{
			bean.setFrequency(dto.getFrequency());
		}
		bean.setSeparation(dto.getSeparation());
		bean.setWhat(dto.getTitle());
		bean.setLocation(dto.getLoc());
		bean.setCalendarId(dto.getCid());
		if (dto.isAd()){
			bean.setStartsOn(dto.getStart());
			bean.setEndsOn(dto.getEnd());
		}else{
			bean.setStartsAt(dto.getStart());
			bean.setEndsAt(dto.getEnd());
		}
		
		//If site calendar event
		if (dto.getCatId()!=null && dto.getCatId() != 0){
			Category category = new Category();
			category.setId(dto.getCatId());
			bean.setCategory(category);
		}
		if (dto.getThbId()!=null && dto.getThbId() != 0){
			File file = new File();
			file.setId(dto.getThbId());
			bean.setFile(file);
		}
		
		Set<EventRecurrence> recurrences = bean.getEventRecurrences();
		recurrences.clear();
		if (dto.getWeekDays()!=null && dto.getWeekDays().length > 0){
			for(Integer day : dto.getWeekDays()){
				EventRecurrence recurrence = new EventRecurrence();
				recurrence.setEvent(bean);
				recurrence.setDay(day);
				recurrences.add(recurrence);
			}
		}
		bean.setEventRecurrences(recurrences);
		
		bean.setPublished(dto.isPublished());
		return bean;
	}
	
	public static Date getDateWithTimeMidnight(Date original){
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(original);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);
		return calEnd.getTime();
	}
	
}
