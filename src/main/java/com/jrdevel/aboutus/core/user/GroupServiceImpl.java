package com.jrdevel.aboutus.core.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.person.PersonServiceImpl;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDAO groupDAO;
	
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);
	
	@Transactional
	public ResultObject list(ListParams params) {
		ListResult<Group> listResult = groupDAO.findAllByCriteria(params);
		ResultObject result = new ResultObject();
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		
		return result;
	}

	@Transactional
	public ResultObject update(Group bean) {
		ResultObject result = new ResultObject();
		
		boolean isUpdate = bean.getId() != null;
		
		//bean.setCustomer(userSession.getCustomer());
		
		if (!isUpdate){
		}
		
		try {
			groupDAO.makePersistent(bean);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in update method");
		}
		
		result.setSuccess(true);
		
		return result;
	}

	@Transactional
	public ResultObject get(Group bean) {
		ResultObject result = new ResultObject();
		
		if (bean == null || bean.getId() == null){
			result.setSuccess(false);
			result.addErrorMessage("Grupo não existe.");
		}else{
			Group group = groupDAO.getGroupById(bean.getId());
			result.setData(group);
		}
		
		return result;
	}

	@Transactional
	public ResultObject delete(List<Group> beans) {
		ResultObject result = new ResultObject();
		
		for (Group group: beans){
			groupDAO.makeTransient(group);
		}
		
		return result;
	}


	public ResultObject insert(Group bean) {
		return null;
	}

}
