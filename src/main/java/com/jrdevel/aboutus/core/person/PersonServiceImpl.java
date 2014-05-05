package com.jrdevel.aboutus.core.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.GenericIdTextView;
import com.jrdevel.aboutus.core.common.model.Address;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.PersonContacts;
import com.jrdevel.aboutus.core.common.model.lists.CivilStatus;
import com.jrdevel.aboutus.core.common.model.lists.ContactType;
import com.jrdevel.aboutus.core.common.model.lists.Country;
import com.jrdevel.aboutus.core.common.model.lists.MemberType;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.dto.GenericIdTextDTO;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDAO personDAO;

	@Transactional
	@Secured("ROLE_LIST_PEOPLE")
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

	@Transactional
	@Secured("ROLE_UPDATE_PEOPLE")
	public ResultObject update(PersonDTO personDTO) {
		return null;
	}

	@Transactional
	public ResultObject insert(PersonDTO personDTO) {
		
		ResultObject result = new ResultObject();
		
		Person entity = new Person();
		
		//Personal data
		entity.setId(null);
		entity.setName(personDTO.getName());
		entity.setMale(personDTO.isMale());
		entity.setCivilStatus(new CivilStatus(personDTO.getCivilStatusValue()));
		entity.setCountry(new Country(personDTO.getNaturalityValue()));
		entity.setBirthday(personDTO.getBirthday());
		entity.setNif(personDTO.getNif());
		entity.setProfession(personDTO.getProfession());
		entity.setMember(personDTO.isMember());
		entity.setMemberType(new MemberType(personDTO.getMemberTypeValue()));
		
		//Church data
		entity.setBaptized(personDTO.isBaptized());
		entity.setBaptismdate(personDTO.getBaptismdate());
		entity.setConsolidated(personDTO.isConsolidated());
		entity.setArrivalChurchDate(personDTO.getArrivalChurchDate());
		entity.setPrecedingChurch(personDTO.getPrecedingChurch());
		
		//Addresses
		if (CollectionUtils.isNotEmpty(personDTO.getAddresses())){
			for (AddressDTO addressDTO : personDTO.getAddresses()){
				Address address = new Address();
				address.setPerson(entity);
				address.setAddress(addressDTO.getAddress());
				address.setCountry(addressDTO.getCountry());
				address.setPostcode(addressDTO.getPostcode());
				address.setState(addressDTO.getState());
				entity.getAddresses().add(address);
			}
		}
		
		//Contacts
		if (CollectionUtils.isNotEmpty(personDTO.getContacts())){
			for (ContactDTO contactDTO : personDTO.getContacts()){
				PersonContacts contact = new PersonContacts();
				contact.setContactType(new ContactType(contactDTO.getContactTypeValue()));
				contact.setValue(contactDTO.getValue());
				entity.getPersonContactses().add(contact);
			}
		}
		
		//Observations
		entity.setObservations(personDTO.getObservations());
		
		//Common data
		entity.setState(0);
		entity.setChurch(UserAuthenticatedManager.getCurrentUser().getChurch());
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		personDAO.makePersistent(entity);
		

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
