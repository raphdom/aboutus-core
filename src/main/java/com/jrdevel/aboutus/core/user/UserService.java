package com.jrdevel.aboutus.core.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.AbstractGenericService;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.util.PasswordGenerator;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class UserService extends AbstractGenericService<User>{
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public ResultObject update(User entity){
		
		ResultObject result = new ResultObject();
		
		boolean isUpdate = entity.getId() != null && entity.getId() != 0;
		
		if (!isUpdate){
			if (existUserEmail(entity.getEmail())){
				result.setSuccess(false);
				result.addErrorMessage("Este email j� existe registado na aplica��o");
				return result;
			}
			entity.setId(null);
			//entity.setCustomer(userSession.getCustomer());
			entity.setRegisterDate(new Date());
			entity.setPassword(PasswordGenerator.passGenerator(8));
		}
		
		userDAO.makePersistent(entity);
		
		result.setSuccess(true);
		
		return result;
		
	}
	
	@Transactional
	public ResultObject list(ListParams params) {
		
		ListResult<UserView> listResult = userDAO.findAllByView(params, UserView.class);
		ResultObject result = newResultObject();
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		
		return result;
	}


	@Transactional
	public ResultObject get(User bean) {
		
		ResultObject result = newResultObject();
		
		if (bean == null || bean.getId() == null){
			result.setSuccess(false);
			result.addErrorMessage("Utilizador n�o existe.");
		}else{
			User user = userDAO.getUserById(bean.getId());
			result.setData(user);
		}
		
		return result;
	}

	@Transactional
	public ResultObject delete(List<User> beans) {
		
		ResultObject result = newResultObject();
		
		for (User user: beans){
			userDAO.makeTransient(user);
		}
		
		return result;
	}
	
	@Transactional
	public List<Permission> getUserPermissions(User bean){
		
		List<Permission> permissions = new ArrayList<Permission>();
		
		User user = userDAO.getUserSimpleById(bean.getId());
		permissions.addAll(user.getPermissions());
		for (Group group : user.getGroups()){
			permissions.addAll(group.getPermissions());
		}
		
		return permissions;
		
	}
	
	//Private methods
	private boolean existUserEmail(String email) {
		
		User bean = userDAO.getUserByEmail(email);
		
		return bean != null;
		
	}


}
