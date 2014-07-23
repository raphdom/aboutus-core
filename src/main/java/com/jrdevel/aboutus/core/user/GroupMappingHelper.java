package com.jrdevel.aboutus.core.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.Permission;

/**
 * @author Raphael Domingues
 *
 */
public class GroupMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static GroupListDTO viewToDTO(GroupListView view){
		GroupListDTO dto = new GroupListDTO();
		dto.setId(view.getId());
		dto.setName(view.getName());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<GroupListDTO> listViewTolistDTO(List<GroupListView> views){
		List<GroupListDTO> dtos = new ArrayList<GroupListDTO>();
		for(GroupListView view : views){
			dtos.add(viewToDTO(view));
		}
		return dtos;
	}
	
	public static GroupDTO beanToDTO(Group bean){
		
		GroupDTO dto = new GroupDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		List<Integer> permissions = new ArrayList<Integer>();
		for (Permission permission : bean.getPermissions()){
			permissions.add(permission.getId());
		}
		dto.setPermissions(permissions);
		
		return dto;
	}
	
	public static Group DTOToBean(GroupDTO dto){
		Group bean = new Group();
		return DTOToBean(dto, bean);
	}
	
	public static Group DTOToBean(GroupDTO dto, Group bean){
		
		Set<Permission> permissions = new HashSet<Permission>();
		if (CollectionUtils.isNotEmpty(dto.getPermissions())){
			for(Integer permissionId : dto.getPermissions()){
				Permission permission = new Permission();
				permission.setId(permissionId);
				permissions.add(permission);
			}
		}
		bean.setPermissions(permissions);
		
		bean.setName(dto.getName());
		
		return bean;
	}

}
