package com.jrdevel.aboutus.core.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDAO personDAO;
	
	
	@Transactional()
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
	}



}
