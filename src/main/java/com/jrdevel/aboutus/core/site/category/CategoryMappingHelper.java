package com.jrdevel.aboutus.core.site.category;

import java.util.ArrayList;
import java.util.List;

import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.model.File;
import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public class CategoryMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static CategoryListDTO viewToDTO(CategoryListView view){
		CategoryListDTO dto = new CategoryListDTO();
		dto.setId(view.getId());
		dto.setParent(new Integer(view.getParent()));
		dto.setText(view.getName());
		dto.setDescription(view.getDescription());
		dto.setPosition(view.getPosition());
		dto.setPublishedAlbuns(view.isPublishedAlbuns());
		dto.setPublishedVideos(view.isPublishedVideos());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<CategoryListDTO> listViewTolistDTO(List<CategoryListView> views){
		List<CategoryListDTO> dtos = new ArrayList<CategoryListDTO>();
		for(CategoryListView view : views){
			if (view.getParent()==0){
				CategoryListDTO node = viewToDTO(view);
				createNodes(views,node);
				dtos.add(node);
			}
		}
		return dtos;
	}
	
	public static CategoryDTO beanToDTO(Category bean){
		
		CategoryDTO dto = new CategoryDTO();
		
		dto.setId(bean.getId());
		dto.setText(bean.getName());
		dto.setParentId(bean.getParent());
		dto.setPosition(bean.getPosition());
		dto.setPublishedAlbuns(bean.isPublishedAlbuns());
		dto.setPublishedVideos(bean.isPublishedVideos());
		dto.setDescription(bean.getDescription());
		
		if (bean.getFile() != null){
			dto.setThumbId(bean.getFile().getId());
		}
		
		return dto;
	}
	
	public static Category DTOToBean(CategoryDTO dto){
		Category bean = new Category();
		return DTOToBean(dto, bean);
	}
	
	public static Category DTOToBean(CategoryDTO dto, Category bean){
		
		bean.setName(dto.getText());
		bean.setParent(dto.getParentId());
		bean.setPosition(dto.getPosition());
		bean.setPublishedAlbuns(dto.isPublishedAlbuns());
		bean.setPublishedVideos(dto.isPublishedVideos());
		bean.setDescription(dto.getDescription());
		
		//Thumb
		File file = new File();
		file.setId(dto.getThumbId());
		bean.setFile(file);
		
		return bean;
	}
	
	private static void createNodes(List<CategoryListView> views, NodeDTO parent) {
		for(CategoryListView view : views){
			if (view.getParent()==parent.getId()){
				CategoryListDTO node = viewToDTO(view);
				createNodes(views,node);
				parent.setLeaf(false);
				parent.addChild(node);
			}
		}
	}

}