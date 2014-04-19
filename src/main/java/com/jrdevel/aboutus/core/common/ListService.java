package com.jrdevel.aboutus.core.common;

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
public class ListService {
	
	private ListDAO listDAO;
	
	/**
	 * Spring use - DI
	 * @param userDAO
	 */
	@Autowired
	public void setListDAO(ListDAO listDAO) {
		this.listDAO = listDAO;
	}
	
	@Transactional
	public List<GenericValueTextDTO> getList(int listType){
		return listDAO.getList(listType);
	}

}
