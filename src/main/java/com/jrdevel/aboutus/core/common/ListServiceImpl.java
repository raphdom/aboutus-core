package com.jrdevel.aboutus.core.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.dto.GenericValueTextDTO;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class ListServiceImpl implements ListService{
	
	@Autowired
	private ListDAO listDAO;

	@Transactional
	public List<GenericValueTextDTO> getCivilStatus() {
		
		List<GenericValueTextView> listView = listDAO.getCivilStatusList();
		
		return beansToDTOs(listView);
		
	}

	@Transactional
	public List<GenericValueTextDTO> getCountry() {
		
		List<GenericValueTextView> listView = listDAO.getCountryList();
		
		return beansToDTOs(listView);
		
	}
	
	@Transactional
	public List<GenericValueTextDTO> getMemberType() {

		List<GenericValueTextView> listView = listDAO.getMemberTypeList();
		
		return beansToDTOs(listView);
		
	}
	
	@Transactional
	public List<GenericValueTextDTO> getContactType() {

		List<GenericValueTextView> listView = listDAO.getContactTypeList();
		
		return beansToDTOs(listView);
		
	}
	
	private List<GenericValueTextDTO> beansToDTOs(List<GenericValueTextView> beans){
		List<GenericValueTextDTO> dtos = new ArrayList<GenericValueTextDTO>();
		
		for(GenericValueTextView bean : beans){
			GenericValueTextDTO dto = new GenericValueTextDTO();
			dto.setValue(bean.getValue());
			dto.setText(bean.getText());
			dtos.add(dto);
		}
		
		return dtos;
	}

}
