package com.jrdevel.aboutus.core.user;

import java.util.List;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.model.Group;
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

	public ResultObject update(Group input) {
		return null;
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_LIST_GROUPS')")
	public ResultObject list(ListParams params) {

		ResultObject result = new ResultObject();

		ListResult<GroupListView> listResult = groupDAO.findAllByView(params, GroupListView.class);

		List<GroupListDTO> dtos = GroupMappingHelper.listViewTolistDTO(listResult.getData());

		result.setData(dtos);
		result.setTotal(listResult.getTotal());

		return result;
	}

	@Transactional
	public ResultObject get(Integer id) {

		ResultObject result = new ResultObject();

		Group bean = groupDAO.findById(id, false);

		if (bean != null && bean.getId() != null){

			GroupDTO dto = GroupMappingHelper.beanToDTO(bean);

			result.setData(dto);

		}else{
			result.setSuccess(false);
			result.addErrorMessage("Grupo não existe.");
		}

		return result;
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_EDIT_GROUPS')")
	public ResultObject update(GroupDTO dto) {
		
		ResultObject result = new ResultObject();

		Group entity = groupDAO.findById(dto.getId(), false);

		GroupMappingHelper.DTOToBean(dto, entity);

		try {
			groupDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in update method");
		}

		return result;
		
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_ADD_GROUPS')")
	public ResultObject insert(GroupDTO dto) {
		
		ResultObject result = new ResultObject();

		Group entity = GroupMappingHelper.DTOToBean(dto);
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			groupDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}

		return result;
	}

	@Transactional
	public ResultObject save(GroupDTO dto) {
		if (dto.getId() != null && dto.getId() != 0){
			return update(dto);
		}else{
			return insert(dto);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_DEL_GROUPS')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();

		for (Integer id: beans){
			Group group = groupDAO.findById(id, false);
			groupDAO.makeTransient(group);
		}

		result.addInfoMessage("Grupo(s) eliminados com sucesso");

		return result;
		
	}
	

}
