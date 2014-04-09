package com.jrdevel.aboutus.core.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.AbstractGenericService;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class GroupServiceImpl extends AbstractGenericService<Group> implements GroupService{
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Transactional
	public ResultObject list(ListParams params) {
		ListResult<Group> listResult = groupDAO.findAllByCriteria(params);
		ResultObject result = newResultObject();
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
		
		groupDAO.makePersistent(bean);
		
		result.setSuccess(true);
		
		return result;
	}

	@Transactional
	public ResultObject get(Group bean) {
		ResultObject result = newResultObject();
		
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
		ResultObject result = newResultObject();
		
		for (Group group: beans){
			groupDAO.makeTransient(group);
		}
		
		return result;
	}


	@Override
	public ResultObject insert(Group bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
