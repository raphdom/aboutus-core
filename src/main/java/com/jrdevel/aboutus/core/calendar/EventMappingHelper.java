package com.jrdevel.aboutus.core.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.model.Event;
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
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(view.getStartsOn());
			calStart.set(Calendar.HOUR_OF_DAY, 0);
			calStart.set(Calendar.MINUTE, 0);
			calStart.set(Calendar.SECOND, 0);
			calStart.set(Calendar.MILLISECOND, 0);
			dto.setStart(calStart.getTime());
			
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(view.getEndsOn());
			calEnd.set(Calendar.HOUR_OF_DAY, 0);
			calEnd.set(Calendar.MINUTE, 0);
			calEnd.set(Calendar.SECOND, 0);
			calEnd.set(Calendar.MILLISECOND, 0);
			dto.setEnd(calEnd.getTime());
			
			dto.setAd(true);
		}
		dto.setTitle(view.getWhat());
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
		bean.setPublished(dto.isPublished());
		return bean;
	}
	
}
