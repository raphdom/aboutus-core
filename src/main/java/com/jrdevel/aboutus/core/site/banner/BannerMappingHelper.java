package com.jrdevel.aboutus.core.site.banner;

import java.util.ArrayList;
import java.util.List;

import com.jrdevel.aboutus.core.common.model.Banner;
import com.jrdevel.aboutus.core.common.model.File;

/**
 * @author Raphael Domingues
 *
 */
public class BannerMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static BannerListDTO viewToDTO(BannerListView view){
		BannerListDTO dto = new BannerListDTO();
		dto.setId(view.getId());
		dto.setName(view.getName());
		dto.setAlias(view.getAlias());
		dto.setLink(view.getLink());
		dto.setPublishUp(view.getPublishUp());
		dto.setPublishDown(view.getPublishDown());
		dto.setOrdering(view.getOrdering());
		return dto;
	}
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static BannerListDTO viewToDTO(BannerListSiteView view){
		BannerListDTO dto = new BannerListDTO();
		dto.setId(view.getId());
		dto.setLink(view.getLink());
		dto.setThumbId(view.getThumbId());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<BannerListDTO> listViewTolistDTO(List<BannerListView> views){
		List<BannerListDTO> dtos = new ArrayList<BannerListDTO>();
		for(BannerListView view : views){
			BannerListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<BannerListDTO> listSiteViewTolistDTO(List<BannerListSiteView> views){
		List<BannerListDTO> dtos = new ArrayList<BannerListDTO>();
		for(BannerListSiteView view : views){
			BannerListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	public static BannerDTO beanToDTO(Banner bean){
		
		BannerDTO dto = new BannerDTO();
		
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setAlias(bean.getAlias());
		dto.setLink(bean.getLink());
		dto.setPublishUp(bean.getPublishUp());
		dto.setPublishDown(bean.getPublishDown());
		dto.setOrdering(bean.getOrdering());
		if (bean.getFile() != null){
			dto.setBannerId(bean.getFile().getId());
		}
		
		return dto;
	}
	
	public static Banner DTOToBean(BannerDTO dto){
		Banner bean = new Banner();
		return DTOToBean(dto, bean);
	}
	
	public static Banner DTOToBean(BannerDTO dto, Banner bean){
		
		bean.setId(dto.getId());
		bean.setName(dto.getName());
		bean.setAlias(dto.getAlias());
		bean.setLink(dto.getLink());
		bean.setPublishUp(dto.getPublishUp());
		bean.setPublishDown(dto.getPublishDown());
		bean.setOrdering(dto.getOrdering());
		
		//Banner
		File file = new File();
		file.setId(dto.getBannerId());
		bean.setFile(file);
		
		return bean;
	}
	
}
