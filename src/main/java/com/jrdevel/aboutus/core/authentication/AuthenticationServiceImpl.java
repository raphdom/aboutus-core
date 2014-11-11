package com.jrdevel.aboutus.core.authentication;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.church.ChurchServiceImpl;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.helper.EmailHelper;
import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.model.Customer;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.Plan;
import com.jrdevel.aboutus.core.common.model.PlanParam;
import com.jrdevel.aboutus.core.common.model.Register;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.person.PersonServiceImpl;
import com.jrdevel.aboutus.core.user.PermissionDAO;
import com.jrdevel.aboutus.core.user.UserDAO;
import com.jrdevel.aboutus.core.util.PasswordGenerator;

/**
 * @author Raphael Domingues
 *
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RegisterDAO registerDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private PermissionDAO permissionDAO;
	
	private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);

	private ChurchServiceImpl churchService;
	
	private PersonServiceImpl personService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDAO.getUserByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(MessageHelper.getMessage(MessageKeyEnum.AUTHENTICATION_FAILED));
		}
		
		List<Permission> permissions = new ArrayList<Permission>();

		permissions.addAll(user.getPermissions());
		for (Group group : user.getGroups()){
			permissions.addAll(group.getPermissions());
		}
		
		HashMap<String, Integer> planParams = new HashMap<String, Integer>();
		for(PlanParam param : user.getCustomer().getPlan().getPlanParams()){
			planParams.put(param.getParam(),Integer.parseInt(param.getValue()));
		}
		
		UserDetailsAdapter userAdapter = new UserDetailsAdapter(user,permissions);
		userAdapter.setPlanParams(planParams);
		
		return userAdapter;
	}
	

	/*@Transactional
	public ResultObject login(User user){
		ResultObject result = new ResultObject();

		User userDB = userDAO.getUserByEmail(user.getEmail());

		if (userDB!=null && user.getPassword().equals(userDB.getPassword())){
			if (!userDB.isActivation() && userDB.getCustomer()==null){
				Register register = getRegister(userDB);
				Customer customer = createCustomer(register);
				Church church = createChurch(register, customer);
				Person person = createPerson(register, church, customer);

				userDB.setCustomer(customer);
				userDB.setChurch(church);
				userDB.setPerson(person);

				userDB.setActivation(true);
				userDB.setLastvisitDate(new Date());

				userDAO.makePersistent(userDB);

			}else if (!userDB.isActivation() && userDB.getCustomer()!=null){
				//TODO abrir a janela de altera��o de palavra-passe
			}else{
				userDB.setLastvisitDate(new Date());
				userDAO.makePersistent(userDB);
			}
			User userSession = new User();
			userSession.setId(user.getId());
			userSession.setEmail(user.getEmail());
			userSession.setCustomer(user.getCustomer());
			result.setData(userSession);
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Nome do utilizador ou palavra-chave incorreta.");
			return result;
		}

		return result;
	}*/
	
	@Secured("ROLE_AUTHENTICATED_USER")
	@Transactional
	public void updateLogin(Integer id){
		User userDB = userDAO.findById(id, false);
		userDB.setLastvisitDate(new Date());
		try {
			userDAO.makePersistent(userDB,false,true);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in update method");
		}
	}
	
	private Register getRegister(User user){
		return registerDAO.getRegisterByUser(user);
	}

	private Customer createCustomer(Register register){

		Plan plan = new Plan();
		plan.setId(1);

		Customer customer = new Customer();
		customer.setName(register.getChurchName());
		customer.setPlan(plan);

		try {
			customerDAO.makePersistent(customer);
		} catch (PlanExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customer;
	}

	private Church createChurch(Register register, Customer customer){

		Church church = new Church();
		church.setName(register.getChurchName());
		church.setCompleteName(register.getChurchName());
		church.setAddress(register.getChurchAddress());
		church.setCountry(register.getCountry());
		church.setCustomer(customer);

		churchService.update(church);

		return church;
	}

	private Person createPerson(Register register, Church church, Customer customer){

		Person person = new Person();
		person.setName(register.getNameResp());
		person.setMember(true);
		person.setChurch(church);
		person.setCustomer(customer);

		//personService.update(person);

		return person;
	}

	/**
	 * Register
	 * @return
	 */
	@Transactional
	public ResultObject register(Register register){

		ResultObject result = new ResultObject();

		//Validations
		//Registo j� existe
		if (registerDAO.existEmailRegistered(register.getEmail()) ||
				userDAO.existEmailRegistered(register.getEmail())){
			result.setSuccess(false);
			result.addErrorMessage("Este email j� existe registado na aplica��o");
			return result;
		}

		register.setRegisterDate(new Date());
		try {
			registerDAO.makePersistent(register);
		} catch (PlanExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = registerUser(register);
		register.setUser(user);
		try {
			registerDAO.makePersistent(register);
		} catch (PlanExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result.setSuccess(true);

		return result;

	}

	public User registerUser(Register register){
		User user = new User();
		user.setEmail(register.getEmail());
		user.setPassword(register.getPassword());
		user.setRegisterDate(register.getRegisterDate());
		try {
			userDAO.makePersistent(user);
		} catch (PlanExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}


	public void logout() {
		
	}


	public ResultObject login(User user) {
		return null;
	}


	@Transactional
	public ResultObject recoverPassword(String email) {
		
		ResultObject result = new ResultObject();
		
		User user = userDAO.getUserByEmail(email);
		
		if (user != null && user.getId()!= null){
			
			String password = PasswordGenerator.passGenerator(8);
			user.setPassword(password);
			
			try {
				userDAO.makePersistent(user,false,false);
			} catch (PlanExceededException e) {
				result.setSuccess(false);
			}
			
			EmailHelper.sendEmail("Sua Palavra-passe","Sua password foi reiniciada e agora é: " + password, user.getEmail());
			
			result.addWarningMessage("Foi lhe enviado um email. Verifique sua caixa de entrada.");
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Não existe este email registado no About Church!");
		}
		
		
		return result;
	}
	
	@Transactional
	public ResultObject listPermissions() {
		
		ResultObject result = new ResultObject();
		
		List<Permission> permissions = permissionDAO.findByCriteria();
		
		List<PermissionDTO> list = new ArrayList<PermissionDTO>();
		
		for(Permission permission : permissions){
			PermissionDTO value = new PermissionDTO();
			value.setId(permission.getId());
			value.setName(MessageHelper.getMessage(permission.getName()));
			list.add(value);
		}
		
		result.setData(list);
		
		return result;
		
	}

}
