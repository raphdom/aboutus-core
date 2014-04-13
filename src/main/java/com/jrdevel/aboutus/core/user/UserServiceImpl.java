package com.jrdevel.aboutus.core.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	private GroupDAO groupDAO;
	
	/*@Secured("ROLE_UPDATE_USERS")
	@Transactional
	public ResultObject update(User entity){
		
		ResultObject result = newResultObject();
		
		userDAO.makePersistent(entity);
		
		return result;
		
	}
	
	@Secured("ROLE_INSERT_USERS")
	@Transactional
	public ResultObject insert(UserView entity){
		
		ResultObject result = new ResultObject();

		if (existUserEmail(entity.getEmail())){
			result.setSuccess(false);
			result.addErrorMessage("Este email já se encontra registado no sistema.");
			return result;
		}
		
		entity.setId(null);
		//entity.setCustomer(userSession.getCustomer());
		entity.setRegisterDate(new Date());
		entity.setPassword(PasswordGenerator.passGenerator(8));

		userDAO.makePersistent(entity);

		return result;
		
	}
	
	@Secured("ROLE_LIST_USERS")
	@Transactional
	public ResultObject list(ListParams params) {
		
		ListResult<UserListView> listResult = userDAO.findAllByView(params, UserListView.class);
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
			result.addErrorMessage("Utilizador não existe.");
		}else{
			UserView user = userDAO.findUniqueByViewAndId(bean.getId(),UserView.class);
			result.setData(user);
		}
		
		return result;
	}

	@Secured("ROLE_DELETE_USERS")
	@Transactional
	public ResultObject delete(List<User> beans) {
		
		ResultObject result = newResultObject();
		
		for (User user: beans){
			userDAO.makeTransient(user);
		}
		
		return result;
	}
	
	/*@Transactional
	public List<Permission> getUserPermissions(User bean){
		
		List<Permission> permissions = new ArrayList<Permission>();
		
		User user = userDAO.getUserSimpleById(bean.getId());
		permissions.addAll(user.getPermissions());
		for (Group group : user.getGroups()){
			permissions.addAll(group.getPermissions());
		}
		
		return permissions;
		
	}*/
	
	@Transactional
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<UserListView> listResult = userDAO.findAllByView(params, UserListView.class);
		
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		
		return result;
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		UserView user = userDAO.findUniqueByViewAndId(id,UserView.class);
		
		if (user != null && user.getId() != null){
			result.setData(user);
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Utilizador não existe.");
		}
		
		return result;
	}
	
	public ResultObject save(UserView bean) {
		if (bean.getId() != null && bean.getId() != 0){
			return update(bean);
		}else{
			return insert(bean);
		}
	}

	@Transactional
	public ResultObject update(UserView bean) {
		return null;
	}

	@Transactional
	public ResultObject insert(UserView bean) {
		return null;
	}

	@Transactional
	public ResultObject delete(List<Integer> beans) {
		return null;
	}
	
	
	//Private methods
	private boolean existUserEmail(String email) {

		User bean = userDAO.getUserByEmail(email);

		return bean != null;

	}

}
