package com.jrdevel.aboutus.core.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.GenericIdTextView;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.dto.GenericIdTextDTO;
import com.jrdevel.aboutus.core.dto.PersonDTO;
import com.jrdevel.aboutus.core.dto.PersonListDTO;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDAO personDAO;

	@Transactional
	public ResultObject list(ListParams params) {
		ResultObject result = new ResultObject();
		
		ListResult<PersonListView> listResult = personDAO.findAllByView(params, PersonListView.class);
		
		List<PersonListDTO> dtos = new ArrayList<PersonListDTO>();
		
		for(PersonListView userBean : listResult.getData()){
			PersonListDTO dto = new PersonListDTO();
			dto.setId(userBean.getId());
			dto.setName(userBean.getName());
			dto.setMale(userBean.isMale());
			dto.setCivilStatus(userBean.getCivilStatus());
			dto.setNaturality(userBean.getNaturality());
			dto.setMember(userBean.isMember());
			dto.setBirthday(userBean.getBirthday());
			dto.setNif(userBean.getNif());
			dto.setProfession(userBean.getProfession());
			dto.setMemberType(userBean.getMemberType());
			dtos.add(dto);
		}
		
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

	public ResultObject get(Integer id) {
		return null;
	}

	public ResultObject update(PersonDTO personDTO) {
		return null;
	}

	public ResultObject insert(PersonDTO personDTO) {
		return null;
	}

	public ResultObject save(PersonDTO personDTO) {
		return null;
	}

	public ResultObject delete(List<Integer> beans) {
		return null;
	}

	/*@Transactional()
	public ResultObject update(Person person){
		ResultObject result = new ResultObject();
		
		boolean isUpdate = person.getId() != null;
		
		if (!isUpdate){
			
		}
		
		personDAO.makePersistent(person);
		
		return result;
	}

	@Transactional
	public ResultObject list(ListParams params) {
		ListResult<PersonView> listResult = personDAO.findAllByView(params, PersonView.class);
		ResultObject result = new ResultObject();
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		//personDAO.teste();
		return result;
	}

	@Transactional
	public ResultObject get(Person bean) {
		
		ResultObject result = new ResultObject();
		
		if (bean == null || bean.getId() == null){
			result.setSuccess(false);
			result.addErrorMessage("Pessoa não existe.");
		}else{
			Person person = personDAO.findById(bean.getId(),false);
			result.setData(person);
		}
		
		return result;
		
	}

	@Transactional
	public ResultObject delete(List<Person> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Person person: beans){
			personDAO.makeTransient(person);
		}
		
		return result;
		
	}


	public ResultObject insert(Person bean) {
		return null;
	}*/



}
