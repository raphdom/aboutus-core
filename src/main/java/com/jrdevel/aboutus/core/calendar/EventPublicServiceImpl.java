package com.jrdevel.aboutus.core.calendar;

import java.util.List;

import net.aboutchurch.common.dto.EventDTO;
import net.aboutchurch.common.dto.EventListDTO;
import net.aboutchurch.common.services.EventPublicService;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Event;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class EventPublicServiceImpl implements EventPublicService{

	private static final long serialVersionUID = 8265895777657128808L;
	
	@Autowired
	private EventDAO eventDAO;

	@Transactional
	public ResultObject list(Integer limit) {

		ResultObject result = new ResultObject();
		
		List<EventListView> events = eventDAO.getHomePageEvents();
		
		List<EventListDTO> dtos = EventMappingHelper.listViewTolistDTO(events);
		
		result.setData(dtos);
		
		return result;
	}

	@Transactional
	public ResultObject get(Integer id) {
		ResultObject result = new ResultObject();
		
		Event eventDB = eventDAO.findById(id, false);
		
		if (eventDB != null && eventDB.getId() != null){
			
			EventDTO dto = EventMappingHelper.beanToDTO(eventDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Evento não existe.");
		}
		
		return result;
	}
	
	

}
