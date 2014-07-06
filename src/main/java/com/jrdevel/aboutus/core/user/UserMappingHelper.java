package com.jrdevel.aboutus.core.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
public class UserMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static UserListDTO viewToDTO(UserListView view){
		UserListDTO dto = new UserListDTO();
		dto.setId(view.getId());
		dto.setEmail(view.getEmail());
		dto.setPersonName(view.getPersonName());
		dto.setChurchName(view.getChurchName());
		dto.setBlock(view.isBlock());
		dto.setActivation(view.isActivation());
		dto.setLastvisitDate(view.getLastvisitDate());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<UserListDTO> listViewTolistDTO(List<UserListView> views){
		List<UserListDTO> dtos = new ArrayList<UserListDTO>();
		for(UserListView view : views){
			dtos.add(viewToDTO(view));
		}
		return dtos;
	}
	
	public static UserDTO beanToDTO(User bean){
		
		UserDTO dto = new UserDTO();
		dto.setId(bean.getId());
		dto.setEmail(bean.getEmail());
		dto.setLanguage(bean.getLocale());
		if (bean.getFile()!=null){
			dto.setAvatarId(bean.getFile().getId());
		}
		if (bean.getPerson() != null){
			dto.setPersonId(bean.getPerson().getId());
		}
		dto.setChurchId(bean.getChurch().getId());
		List<Integer> groups = new ArrayList<Integer>();
		for (Group group : bean.getGroups()){
			groups.add(group.getId());
		}
		dto.setGroups(groups);
		List<Integer> permissions = new ArrayList<Integer>();
		for (Permission permission : bean.getPermissions()){
			permissions.add(permission.getId());
		}
		dto.setPermissions(permissions);
		
		return dto;
	}
	
	public static User DTOToBean(UserDTO dto){
		User bean = new User();
		return DTOToBean(dto, bean);
	}
	
	public static User DTOToBean(UserDTO dto, User bean){
		
		if (dto.getPersonId() != null){
			Person person = new Person();
			person.setId(dto.getPersonId());
			bean.setPerson(person);
		}
		
		if (dto.getChurchId() != null){
			Church church = new Church();
			church.setId(dto.getChurchId());
			bean.setChurch(church);
		}
		
		Set<Group> groups = new HashSet<Group>();
		if (CollectionUtils.isNotEmpty(dto.getGroups())){
			for(Integer groupId : dto.getGroups()){
				Group group = new Group();
				group.setId(groupId);
				groups.add(group);
			}
		}
		bean.setGroups(groups);
		
		Set<Permission> permissions = new HashSet<Permission>();
		if (CollectionUtils.isNotEmpty(dto.getPermissions())){
			for(Integer permissionId : dto.getPermissions()){
				Permission permission = new Permission();
				permission.setId(permissionId);
				permissions.add(permission);
			}
		}
		bean.setPermissions(permissions);
		
		bean.setEmail(dto.getEmail());
		
		return bean;
	}

}
