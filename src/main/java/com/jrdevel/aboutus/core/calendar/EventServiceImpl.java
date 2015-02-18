package com.jrdevel.aboutus.core.calendar;

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.aboutchurch.common.dto.EventDTO;
import net.aboutchurch.common.dto.EventListDTO;
import net.aboutchurch.common.to.ResultObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.AboutChurchDateHelper;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.helper.EmailSender;
import com.jrdevel.aboutus.core.common.model.Event;
import com.jrdevel.aboutus.core.common.model.EventPeople;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.PersonContacts;
import com.jrdevel.aboutus.core.person.PersonDAO;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class EventServiceImpl implements EventService{
	
	private static final Logger logger = Logger.getLogger(EventServiceImpl.class);
	
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private	EmailSender emailSender;
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(Date start, Date end) {
		
		ResultObject result = new ResultObject();
		
		//params.setLimit(-1);
		
		List<EventListView> events = eventDAO.callProcedure(start,end);
		
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
			result.addErrorMessage("Categoria não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(EventDTO eventDTO) {
		
		if (eventDTO.getId() != null && eventDTO.getId() != 0){
			return update(eventDTO);
		}else{
			return insert(eventDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_EVENTS')")
	public ResultObject insert(EventDTO eventDTO) {
		
		ResultObject result = new ResultObject();
		
		Event entity = EventMappingHelper.DTOToBean(eventDTO);
		
		//Insert data
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			eventDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		if (CollectionUtils.isNotEmpty(entity.getEventPeoples())){
			String date = AboutChurchDateHelper.getDateFormatted(entity.getStartsAt(),entity.getEndsAt());
			for (EventPeople evtPerson : entity.getEventPeoples()){
				Person person = personDAO.findById(evtPerson.getPerson().getId(),false);
				
				sendEmail(person.getPersonContactses(),entity.getWhat(),
						date,entity.getLocation(),entity.getDescription());
			}
		}
		
		return result;
		
	}
	
	private void sendEmail(Set<PersonContacts> contacts, String what, 
			String when, String where, String description){
		
		for (PersonContacts contact : contacts){
			if (contact.getContactType().getId().equals("email")){
				emailSender.sendEmail("[AboutChurch] Evento: " + what, 
						"templates/newEvent.vm", contact.getValue(), what, when, where, description);
			}
		}
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_EVENTS')")
	public ResultObject update(EventDTO eventDTO) {
		
		ResultObject result = new ResultObject();

		Event entity = eventDAO.findById(eventDTO.getEid(), false);

		EventMappingHelper.DTOToBean(eventDTO, entity);

		try {
			eventDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in update method");
		}

		return result;
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_DEL_EVENTS')")
	public ResultObject delete(Integer eventId) {
		
		ResultObject result = new ResultObject();
		
		Event event = eventDAO.findById(eventId, false);
		eventDAO.makeTransient(event);

		result.addInfoMessage("Evento eliminado com sucesso");
		
		return result;
		
	}
	

}
