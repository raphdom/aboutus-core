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
		
		List<GenericValueTextDTO> dtos = new ArrayList<GenericValueTextDTO>();
		
		for(GenericValueTextView view : listView){
			GenericValueTextDTO dto = new GenericValueTextDTO();
			dto.setValue(view.getValue());
			dto.setText(view.getText());
			dtos.add(dto);
		}
		
		return dtos;
		
	}

	@Transactional
	public List<GenericValueTextDTO> getCountry() {
		
		List<GenericValueTextView> listView = listDAO.getCountryList();
		
		List<GenericValueTextDTO> dtos = new ArrayList<GenericValueTextDTO>();
		
		for(GenericValueTextView view : listView){
			GenericValueTextDTO dto = new GenericValueTextDTO();
			dto.setValue(view.getValue());
			dto.setText(view.getText());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	@Transactional
	public List<GenericValueTextDTO> getMemberType() {

		List<GenericValueTextView> listView = listDAO.getMemberTypeList();
		
		List<GenericValueTextDTO> dtos = new ArrayList<GenericValueTextDTO>();
		
		for(GenericValueTextView view : listView){
			GenericValueTextDTO dto = new GenericValueTextDTO();
			dto.setValue(view.getValue());
			dto.setText(view.getText());
			dtos.add(dto);
		}
		
		return dtos;
	}

}
