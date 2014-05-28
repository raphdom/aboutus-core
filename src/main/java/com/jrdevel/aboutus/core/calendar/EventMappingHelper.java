package com.jrdevel.aboutus.core.calendar;

import java.util.ArrayList;
import java.util.List;

import com.jrdevel.aboutus.core.common.model.Event;

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
		dto.setStart(view.getStartsAt());
		dto.setEnd(view.getEndsAt());
		dto.setTitle(view.getWhat());
		dto.setCid(1);
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
		bean.setFrequency(dto.getFrequency());
		bean.setSeparation(dto.getSeparation());
		bean.setWhat(dto.getTitle());
		bean.setCalendarId(dto.getCid());
		return bean;
	}
	
}
