package com.jrdevel.aboutus.core.person;

import java.util.ArrayList;
import java.util.List;

import net.aboutchurch.common.dto.GenericIdTextDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.GenericIdTextView;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.model.Person;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDAO personDAO;
	
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

	@Transactional
	@Secured("ROLE_LIST_PEOPLE")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<PersonListView> listResult = personDAO.findAllByView(params, PersonListView.class);
		
		List<PersonListDTO> dtos = PersonMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
	}
	
	@Transactional
	public ResultObject listNames() {
		
		ResultObject result = new ResultObject();
		
		List<GenericIdTextDTO> dtos = new ArrayList<GenericIdTextDTO>();
		
		ListResult<GenericIdTextView> listResult = personDAO.findComboList();
		
		for(GenericIdTextView view : listResult.getData()){
			GenericIdTextDTO dto = new GenericIdTextDTO();
			dto.setValue(view.getValue());
			dto.setText(view.getText());
			dtos.add(dto);
		}
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
	}

	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Person personDB = personDAO.findById(id, false);
		
		if (personDB != null && personDB.getId() != null){
			
			PersonDTO dto = PersonMappingHelper.beanToDTO(personDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Pessoa não existe.");
		}
		
		return result;
		
	}

	@Transactional
	@Secured("ROLE_EDIT_PEOPLE")
	public ResultObject update(PersonDTO personDTO) {
		
		ResultObject result = new ResultObject();
		
		Person person = personDAO.findById(personDTO.getId(), false);
		
		if (person != null && person.getId() != null){
			
			person.getAddresses().clear();
			person.getPersonContactses().clear();
			
			person = PersonMappingHelper.DTOToBean(personDTO, person);
			
			try {
				personDAO.makePersistent(person);
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
		}
		
		return result;
	}

	@Transactional
	@Secured("ROLE_ADD_PEOPLE")
	public ResultObject insert(PersonDTO personDTO) {
		
		ResultObject result = new ResultObject();
		
		Person entity = PersonMappingHelper.DTOToBean(personDTO);
		
		//Insert data
		entity.setId(null);
		entity.setState(0);
		entity.setChurch(UserAuthenticatedManager.getCurrentUser().getChurch());
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			personDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.addPlanExceededMessage("O seu plano não permite adicionar mais pessoas. Faça um update de plano.");
			result.setSuccess(false);
		}

		return result;
		
	}

	@Transactional
	public ResultObject save(PersonDTO personDTO) {
		if (personDTO.getId() != null && personDTO.getId() != 0){
			return update(personDTO);
		}else{
			return insert(personDTO);
		}
	}

	@Transactional
	@Secured("ROLE_DELETE_PEOPLE")
	public ResultObject delete(List<Integer> beans) {
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Person person = personDAO.findById(id, false);
			personDAO.makeTransient(person);
		}
		
		result.addInfoMessage("Pessoas eliminadas com sucesso");
		
		return result;
	}

}
