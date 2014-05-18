package com.jrdevel.aboutus.core.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.model.Person;
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
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_LIST_USERS')")
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
		
		if (existUserEmail(userDTO)){
			ResultObject result = new ResultObject();
			result.setSuccess(false);
			result.addErrorMessage(
					MessageHelper.getMessage(MessageKeyEnum.DULICATED_EMAIL));
			return result;
		}
		if (userDTO.getId() != null && userDTO.getId() != 0){
			return update(userDTO);
		}else{
			return insert(userDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_USERS')")
	public ResultObject insert(UserDTO userDTO) {
		
		ResultObject result = new ResultObject();
		
		User entity = new User();
		
		if (userDTO.getPersonId() != null){
			Person person = new Person();
			person.setId(userDTO.getPersonId());
			entity.setPerson(person);
		}
		
		Church church = new Church();
		church.setId(userDTO.getChurchId());
		entity.setChurch(church);
		
		entity.setId(null);
		entity.setEmail(userDTO.getEmail());
		entity.setRegisterDate(new Date());
		entity.setPassword(PasswordGenerator.passGenerator(8));
		
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		userDAO.makePersistent(entity);

		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_USERS')")
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
	@PreAuthorize("hasAuthority('ROLE_DELETE_USERS')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			User user = userDAO.findById(id, false);
			userDAO.makeTransient(user);
		}
		
		result.addInfoMessage("Utilizador(es) eliminados com sucesso");
		
		return result;
		
	}
	
	
	//Private methods
	@Transactional
	private boolean existUserEmail(UserDTO dto) {

		User bean = userDAO.getUserByEmail(dto.getEmail());
		
		boolean isUpdate = dto.getId() != null && dto.getId() != 0;
		
		if (bean == null || 
				(bean.getEmail().equals(dto.getEmail()) && isUpdate)){
			return false;
		}else{
			return true;
		}

	}

}
