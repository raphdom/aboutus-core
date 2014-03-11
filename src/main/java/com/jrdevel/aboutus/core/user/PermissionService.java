package com.jrdevel.aboutus.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PermissionService {
	
	private PermissionDAO permissionDAO;
	
	/**
	 * Spring use - DI
	 * @param userDAO
	 */
	@Autowired
	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}
	
	
	/**
	 * Get all users
	 * @return
	 */
	@Transactional
	public ListResult<Permission> getList(ListParams params){

		return permissionDAO.findAllByCriteria(params);
	}



}
