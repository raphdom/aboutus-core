package com.jrdevel.aboutus.core.site.album;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.jrdevel.aboutus.core.common.model.Album;
import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.model.File;
import com.jrdevel.aboutus.core.common.model.ItemAlbum;

/**
 * @author Raphael Domingues
 *
 */
public class AlbumMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static AlbumListDTO viewToDTO(AlbumListView view){
		AlbumListDTO dto = new AlbumListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setOrdering(view.getOrdering());
		dto.setPublished(view.isPublished());
		dto.setCategoryName(view.getCategoryName());
		return dto;
	}
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static AlbumListDTO viewToDTO(AlbumListSiteView view){
		AlbumListDTO dto = new AlbumListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setThumbId(view.getThumbId());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<AlbumListDTO> listViewTolistDTO(List<AlbumListView> views){
		List<AlbumListDTO> dtos = new ArrayList<AlbumListDTO>();
		for(AlbumListView view : views){
			AlbumListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<AlbumListDTO> listSiteViewTolistDTO(List<AlbumListSiteView> views){
		List<AlbumListDTO> dtos = new ArrayList<AlbumListDTO>();
		for(AlbumListSiteView view : views){
			AlbumListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	public static AlbumDTO beanToDTO(Album bean){
		
		AlbumDTO dto = new AlbumDTO();
		dto.setId(bean.getId());
		dto.setTitle(bean.getTitle());
		dto.setOrdering(bean.getOrdering());
		dto.setDescription(bean.getDescription());
		dto.setPublished(bean.isPublished());
		if (bean.getCategory()!= null){
			dto.setCategoryId(bean.getCategory().getId());
		}
		if (bean.getFile() != null){
			dto.setThumbId(bean.getFile().getId());
		}
		//Album items
		if (CollectionUtils.isNotEmpty(bean.getItemAlbums())){
			List<ItemAlbumDTO> items = new ArrayList<ItemAlbumDTO>();
			for(ItemAlbum it : bean.getItemAlbums()){
				ItemAlbumDTO itdto = new ItemAlbumDTO();
				itdto.setId(it.getFile().getId());
				itdto.setOrdering(it.getPosition());
				itdto.setTitle(it.getTitle());
				itdto.setDescription(it.getDescription());
				items.add(itdto);
			}
			dto.setItems(items);
		}
		
		return dto;
	}
	
	public static Album DTOToBean(AlbumDTO dto){
		Album bean = new Album();
		return DTOToBean(dto, bean);
	}
	
	public static Album DTOToBean(AlbumDTO dto, Album bean){
		
		bean.setId(dto.getId());
		bean.setTitle(dto.getTitle());
		bean.setOrdering(dto.getOrdering());
		bean.setDescription(dto.getDescription());
		bean.setPublished(dto.isPublished());
		//Category
		Category category = new Category();
		category.setId(dto.getCategoryId());
		bean.setCategory(category);
		
		//Thumb
		File file = new File();
		file.setId(dto.getThumbId());
		bean.setFile(file);
		
		//Album items
		if(CollectionUtils.isNotEmpty(dto.getItems())){
			Set<ItemAlbum> itemsBean = new HashSet<ItemAlbum>();
			for (ItemAlbumDTO itemDTO : dto.getItems()){
				ItemAlbum item = new ItemAlbum();
				item.setAlbum(bean);
				File itemFile = new File();
				itemFile.setId(itemDTO.getId());
				item.setFile(itemFile);
				item.setPosition(itemDTO.getOrdering());
				item.setTitle(itemDTO.getTitle());
				item.setDescription(itemDTO.getDescription());
				itemsBean.add(item);
			}
			bean.setItemAlbums(itemsBean);
		}
		
		return bean;
	}
	
}
