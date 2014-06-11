package com.jrdevel.aboutus.core.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.helper.EmailHelper;
import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.File;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.person.PersonDTO;
import com.jrdevel.aboutus.core.person.PersonMappingHelper;
import com.jrdevel.aboutus.core.person.PersonService;
import com.jrdevel.aboutus.core.util.PasswordGenerator;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PersonService personService;

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_LIST_USERS')")
	public ResultObject list(ListParams params) {

		ResultObject result = new ResultObject();

		ListResult<UserListView> listResult = userDAO.findAllByView(params, UserListView.class);

		List<UserListDTO> dtos = UserMappingHelper.listViewTolistDTO(listResult.getData());

		result.setData(dtos);
		result.setTotal(listResult.getTotal());

		return result;
	}

	@Transactional
	public ResultObject get(Integer id) {

		ResultObject result = new ResultObject();

		User userDB = userDAO.getUserById(id);

		if (userDB != null && userDB.getId() != null){

			UserDTO dto = UserMappingHelper.beanToDTO(userDB);

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

		User entity = UserMappingHelper.DTOToBean(userDTO);

		entity.setId(null);
		entity.setRegisterDate(new Date());
		String password = PasswordGenerator.passGenerator(8);
		entity.setPassword(password);

		entity.setLocale("en_GB");
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			userDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}

		EmailHelper.sendEmail("Sua password é: " + password, entity.getEmail());

		return result;

	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_USERS')")
	public ResultObject update(UserDTO userDTO) {

		ResultObject result = new ResultObject();

		User entity = userDAO.findById(userDTO.getId(), false);

		UserMappingHelper.DTOToBean(userDTO, entity);

		try {
			userDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in update method");
		}

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

	@Transactional
	public ResultObject getCurrentProfile() {

		ResultObject result = new ResultObject();

		User userDB = userDAO.getUserById(UserAuthenticatedManager.getCurrentUser().getId());

		UserDTO userDTO = UserMappingHelper.beanToDTO(userDB);
		PersonDTO personDTO = PersonMappingHelper.beanToDTO(userDB.getPerson());

		HashMap<String,Object> objects = new HashMap<String,Object>();

		objects.put("user",userDTO);
		objects.put("person",personDTO);

		result.setData(objects);

		return result;
	}
	
	@Transactional
	public ResultObject updateProfile(ProfileDTO profileDTO) {
		ResultObject result = new ResultObject();
		
		if (existUserEmail(profileDTO.getUser())){
			result.setSuccess(false);
			result.addErrorMessage(
					MessageHelper.getMessage(MessageKeyEnum.DULICATED_EMAIL));
		}else{
			
			User entity = userDAO.findById(profileDTO.getUser().getId(), false);

			UserMappingHelper.DTOToBean(profileDTO.getUser(), entity);
			
			if (profileDTO.getUser().getAvatarId()!=null){
				File file = new File();
				file.setId(profileDTO.getUser().getAvatarId());
				entity.setFile(file);
			}
			if (!StringUtils.isEmpty(profileDTO.getUser().getLanguage())){
				entity.setLocale(profileDTO.getUser().getLanguage());
			}

			try {
				userDAO.makePersistent(entity);
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
			personService.update(profileDTO.getPerson());
			
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject changePassword(String passActual, String passNew) {
		
		ResultObject result = new ResultObject();
		
		User userDB = userDAO.getUserById(UserAuthenticatedManager.getCurrentUser().getId());
		
		if (userDB.getPassword().equals(passActual)){
			userDB.setPassword(passNew);
			
			try {
				userDAO.makePersistent(userDB,false,false);
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
			result.addInfoMessage("Palavra-chave alterada com sucesso.");
		}else{
			result.setSuccess(false);
			result.addErrorMessage("A Palavra chave actual não está correta");
		}
		
		return result;
	}

	//Private methods
	
	private boolean existUserEmail(UserDTO dto) {

		boolean isUpdate = dto.getId() != null && dto.getId() != 0;
		
		User bean = null;
		
		if (isUpdate){
			bean = userDAO.getUserById(dto.getId());
		}else{
			bean = userDAO.getUserByEmail(dto.getEmail());
		}
		
		if (bean == null || 
				(bean.getEmail().equals(dto.getEmail()) && isUpdate)){
			return false;
		}else{
			return true;
		}

	}

}
