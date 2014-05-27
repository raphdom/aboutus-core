package com.jrdevel.aboutus.core.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Event;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	private EventDAO eventDAO;
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
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
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(EventDTO eventDTO) {
		
		ResultObject result = new ResultObject();
		
		Event entity = EventMappingHelper.DTOToBean(eventDTO);
		
		//Insert data
		entity.setId(null);
		//entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		eventDAO.makePersistent(entity);
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(EventDTO categoryDTO) {
		
		ResultObject result = new ResultObject();
		
		
		return result;
	}

	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		
		return result;
		
	}
	

}
