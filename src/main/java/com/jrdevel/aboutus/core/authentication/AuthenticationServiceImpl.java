package com.jrdevel.aboutus.core.authentication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.church.ChurchService;
import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.model.Customer;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.Plan;
import com.jrdevel.aboutus.core.common.model.Register;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.person.PersonService;
import com.jrdevel.aboutus.core.user.UserDAO;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RegisterDAO registerDAO;
	@Autowired
	private CustomerDAO customerDAO;

	private ChurchService churchService;
	
	private PersonService personService;


	@Transactional
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

		customerDAO.makePersistent(customer);

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
		person.setIsMember(true);
		person.setChurch(church);
		person.setCustomer(customer);

		personService.update(person);

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
		registerDAO.makePersistent(register);
		User user = registerUser(register);
		register.setUser(user);
		registerDAO.makePersistent(register);

		result.setSuccess(true);

		return result;

	}

	public User registerUser(Register register){
		User user = new User();
		user.setEmail(register.getEmail());
		user.setPassword(register.getPassword());
		user.setRegisterDate(register.getRegisterDate());
		userDAO.makePersistent(user);
		return user;
	}

}
