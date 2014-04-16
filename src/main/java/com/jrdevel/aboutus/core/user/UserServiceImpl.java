package com.jrdevel.aboutus.core.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.dto.UserDTO;
import com.jrdevel.aboutus.core.dto.UserListDTO;
import com.jrdevel.aboutus.core.util.PasswordGenerator;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
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
	@Secured("ROLE_LIST_USERS")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<UserListView> listResult = userDAO.findAllByView(params, UserListView.class);
		
		List<UserListDTO> dtos = new ArrayList<UserListDTO>();
		
		for(UserListView userBean : listResult.getData()){
			UserListDTO dto = new UserListDTO();
			dto.setId(userBean.getId());
			dto.setEmail(userBean.getEmail());
			dto.setPersonName(userBean.getPersonName());
			dto.setChurchName(userBean.getChurchName());
			dto.setBlock(userBean.isBlock());
			dto.setActivation(userBean.isActivation());
			dto.setLastvisitDate(userBean.getLastvisitDate());
			dtos.add(dto);
		}
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		User userDB = userDAO.getUserById(id);
		
		if (userDB != null && userDB.getId() != null){
			
			UserDTO dto = new UserDTO();
			dto.setId(userDB.getId());
			dto.setEmail(userDB.getEmail());
			if (userDB.getPerson() != null){
				dto.setPersonId(userDB.getPerson().getId());
			}
			dto.setChurchId(userDB.getChurch().getId());
			List<Integer> groups = new ArrayList<Integer>();
			for (Group group : userDB.getGroups()){
				groups.add(group.getId());
			}
			dto.setGroups(groups);
			List<Integer> permissions = new ArrayList<Integer>();
			for (Permission permission : userDB.getPermissions()){
				permissions.add(permission.getId());
			}
			dto.setPermissions(permissions);
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Utilizador não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(UserDTO userDTO) {
		if (existUserEmail(userDTO.getEmail())){
			ResultObject result = new ResultObject();
			result.setSuccess(false);
			result.addErrorMessage("Este email já se encontra registado no sistema.");
			return result;
		}
		if (userDTO.getId() != null && userDTO.getId() != 0){
			return update(userDTO);
		}else{
			return insert(userDTO);
		}
	}

	@Transactional
	@Secured("ROLE_INSERT_USERS")
	public ResultObject insert(UserDTO userDTO) {
		
		ResultObject result = new ResultObject();

		Person person = new Person();
		person.setId(userDTO.getPersonId());
		
		Church church = new Church();
		church.setId(userDTO.getChurchId());
		
		User entity = new User();
		entity.setId(null);
		entity.setEmail(userDTO.getEmail());
		entity.setRegisterDate(new Date());
		entity.setPassword(PasswordGenerator.passGenerator(8));
		entity.setPerson(person);
		entity.setChurch(church);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		userDAO.makePersistent(entity);

		return result;
		
	}
	
	@Transactional
	@Secured("ROLE_UPDATE_USERS")
	public ResultObject update(UserDTO userDTO) {
		
		ResultObject result = new ResultObject();

		Person person = new Person();
		person.setId(userDTO.getPersonId());
		
		Church church = new Church();
		church.setId(userDTO.getChurchId());
		
		User entity = userDAO.findById(userDTO.getId(), false);
		
		entity.setEmail(userDTO.getEmail());
		entity.setPerson(person);
		entity.setChurch(church);
		
		userDAO.makePersistent(entity);

		return result;
	}

	@Transactional
	@Secured("ROLE_DELETE_USERS")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			User user = userDAO.findById(id, false);
			userDAO.makeTransient(user);
		}
		
		return result;
		
	}
	
	
	//Private methods
	@Transactional
	private boolean existUserEmail(String email) {

		User bean = userDAO.getUserByEmail(email);

		return bean != null;

	}

}
