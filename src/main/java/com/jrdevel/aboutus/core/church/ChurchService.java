package com.jrdevel.aboutus.core.church;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class ChurchService {
	
	private ChurchDAO churchDAO;
	
	/**
	 * Spring use - DI
	 * @param userDAO
	 */
	@Autowired
	public void setChurchDAO(ChurchDAO churchDAO) {
		this.churchDAO = churchDAO;
	}
	
	
	/**
	 * Get all churchs
	 * @return
	 */
	@Transactional
	public ListResult<Church> getChurchList(ListParams params){

		return churchDAO.findAllByCriteria(params);
		
	}
	
	@Transactional()
	public ResultObject update(Church church){
		ResultObject result = new ResultObject();
		
		boolean isUpdate = church.getId() != null;
		
		if (!isUpdate){
			
		}
		
		churchDAO.makePersistent(church);
		
		return result;
	}



}
