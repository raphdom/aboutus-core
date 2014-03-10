package com.jrdevel.aboutus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.AbstractGenericService;
import com.jrdevel.aboutus.core.dao.PersonDAO;
import com.jrdevel.aboutus.core.model.Person;
import com.jrdevel.aboutus.core.util.ListParams;
import com.jrdevel.aboutus.core.util.ListResult;
import com.jrdevel.aboutus.core.util.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PersonService extends AbstractGenericService<Person>{
	
	private PersonDAO personDAO;
	
	/**
	 * Spring use - DI
	 * @param personDAO
	 */
	@Autowired
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	
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
		ListResult<Person> listResult = personDAO.findAllByCriteria(params);
		ResultObject result = newResultObject();
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		
		return result;
	}

	@Transactional
	public ResultObject get(Person bean) {
		
		ResultObject result = newResultObject();
		
		if (bean == null || bean.getId() == null){
			result.setSuccess(false);
			result.addErrorMessage("Pessoa n�o existe.");
		}else{
			Person person = personDAO.findById(bean.getId(),false);
			result.setData(person);
		}
		
		return result;
		
	}

	@Transactional
	public ResultObject delete(List<Person> beans) {
		
		ResultObject result = newResultObject();
		
		for (Person person: beans){
			personDAO.makeTransient(person);
		}
		
		return result;
		
	}



}
