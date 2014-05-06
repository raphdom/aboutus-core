package com.jrdevel.aboutus.core.person;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jrdevel.aboutus.core.common.model.Address;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.PersonContacts;
import com.jrdevel.aboutus.core.common.model.lists.CivilStatus;
import com.jrdevel.aboutus.core.common.model.lists.ContactType;
import com.jrdevel.aboutus.core.common.model.lists.Country;
import com.jrdevel.aboutus.core.common.model.lists.MemberType;

/**
 * @author Raphael Domingues
 *
 */
public class PersonMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static PersonListDTO viewToDTO(PersonListView view){
		PersonListDTO dto = new PersonListDTO();
		dto.setId(view.getId());
		dto.setName(view.getName());
		dto.setMale(view.isMale());
		dto.setCivilStatus(view.getCivilStatus());
		dto.setNaturality(view.getNaturality());
		dto.setMember(view.isMember());
		dto.setBirthday(view.getBirthday());
		dto.setNif(view.getNif());
		dto.setProfession(view.getProfession());
		dto.setMemberType(view.getMemberType());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<PersonListDTO> listViewTolistDTO(List<PersonListView> views){
		List<PersonListDTO> dtos = new ArrayList<PersonListDTO>();
		for(PersonListView view : views){
			dtos.add(viewToDTO(view));
		}
		return dtos;
	}
	
	public static PersonDTO beanToDTO(Person person){
		
		PersonDTO dto = new PersonDTO();
		dto.setId(person.getId());
		dto.setName(person.getName());
		dto.setMale(person.isMale());
		if (person.getCivilStatus()!= null){
			dto.setCivilStatusValue(person.getCivilStatus().getId());
		}
		if (person.getCountry()!= null){
			dto.setNaturalityValue(person.getCountry().getId());
		}
		dto.setBirthday(person.getBirthday());
		dto.setNif(person.getNif());
		dto.setProfession(person.getProfession());
		dto.setMember(person.isMember());
		if (person.getMemberType()!= null){
			dto.setMemberTypeValue(person.getMemberType().getId());
		}
		
		dto.setBaptized(person.isBaptized());
		dto.setBaptismdate(person.getBaptismdate());
		dto.setConsolidated(person.isConsolidated());
		dto.setArrivalChurchDate(person.getArrivalChurchDate());
		dto.setPrecedingChurch(person.getPrecedingChurch());
		
		//Addresses
		if (CollectionUtils.isNotEmpty(person.getAddresses())){
			List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
			for (Address address : person.getAddresses()){
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setAddress(address.getAddress());
				addressDTO.setCountry(address.getCountry());
				addressDTO.setPostcode(address.getPostcode());
				addressDTO.setState(address.getState());
				addressesDTO.add(addressDTO);
			}
			dto.setAddresses(addressesDTO);
		}
		
		//Contacts
		if (CollectionUtils.isNotEmpty(person.getPersonContactses())){
			List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
			for (PersonContacts contact : person.getPersonContactses()){
				ContactDTO contactDTO = new ContactDTO();
				contactDTO.setContactTypeValue(contact.getContactType().getId());
				contactDTO.setValue(contact.getValue());
				contactsDTO.add(contactDTO);
			}
			dto.setContacts(contactsDTO);
		}
		
		//Observartions
		dto.setObservations(person.getObservations());
		
		return dto;
	}
	
	public static Person DTOToBean(PersonDTO dto){
		Person bean = new Person();
		return DTOToBean(dto, bean);
	}
	
	public static Person DTOToBean(PersonDTO dto, Person bean){
		
		//Personal data
		bean.setName(dto.getName());
		bean.setMale(dto.isMale());
		if (!StringUtils.isEmpty(dto.getCivilStatusValue())){
			bean.setCivilStatus(new CivilStatus(dto.getCivilStatusValue()));
		}
		if (!StringUtils.isEmpty(dto.getNaturalityValue())){
			bean.setCountry(new Country(dto.getNaturalityValue()));
		}
		bean.setBirthday(dto.getBirthday());
		bean.setNif(dto.getNif());
		bean.setProfession(dto.getProfession());
		bean.setMember(dto.isMember());
		if (!StringUtils.isEmpty(dto.getMemberTypeValue())){
			bean.setMemberType(new MemberType(dto.getMemberTypeValue()));
		}
		
		//Church data
		bean.setBaptized(dto.isBaptized());
		bean.setBaptismdate(dto.getBaptismdate());
		bean.setConsolidated(dto.isConsolidated());
		bean.setArrivalChurchDate(dto.getArrivalChurchDate());
		bean.setPrecedingChurch(dto.getPrecedingChurch());
		
		//Addresses
		if (CollectionUtils.isNotEmpty(dto.getAddresses())){
			for (AddressDTO addressDTO : dto.getAddresses()){
				Address address = new Address();
				address.setPerson(bean);
				address.setAddress(addressDTO.getAddress());
				address.setCountry(addressDTO.getCountry());
				address.setPostcode(addressDTO.getPostcode());
				address.setState(addressDTO.getState());
				bean.getAddresses().add(address);
			}
		}
		
		//Contacts
		if (CollectionUtils.isNotEmpty(dto.getContacts())){
			for (ContactDTO contactDTO : dto.getContacts()){
				PersonContacts contact = new PersonContacts();
				contact.setPerson(bean);
				contact.setContactType(new ContactType(contactDTO.getContactTypeValue()));
				contact.setValue(contactDTO.getValue());
				bean.getPersonContactses().add(contact);
			}
		}
		
		//Observations
		bean.setObservations(dto.getObservations());
		
		return bean;
	}

}
