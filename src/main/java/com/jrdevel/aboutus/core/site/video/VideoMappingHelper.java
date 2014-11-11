package com.jrdevel.aboutus.core.site.video;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.model.Video;

/**
 * @author Raphael Domingues
 *
 */
public class VideoMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static VideoListDTO viewToDTO(VideoListView view){
		VideoListDTO dto = new VideoListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setOrdering(view.getOrdering());
		dto.setUrl(view.getUrl());
		dto.setPublished(view.isPublished());
		dto.setCategoryName(view.getCategoryName());
		return dto;
	}
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static VideoListDTO viewToDTO(VideoListSiteView view){
		VideoListDTO dto = new VideoListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setUrl(view.getUrl());
		dto.setYoutubeId(getYoutubeIdFromUrl(view.getUrl()));
		return dto;
	}
	
	public static String getYoutubeIdFromUrl(String url){
		String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(url);

	    if(matcher.find()){
	        return matcher.group();
	    }
	    
	    return null;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<VideoListDTO> listViewTolistDTO(List<VideoListView> views){
		List<VideoListDTO> dtos = new ArrayList<VideoListDTO>();
		for(VideoListView view : views){
			VideoListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<VideoListDTO> listSiteViewTolistDTO(List<VideoListSiteView> views){
		List<VideoListDTO> dtos = new ArrayList<VideoListDTO>();
		for(VideoListSiteView view : views){
			VideoListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	public static VideoDTO beanToDTO(Video bean){
		
		VideoDTO dto = new VideoDTO();
		
		dto.setId(bean.getId());
		dto.setTitle(bean.getTitle());
		dto.setOrdering(bean.getOrdering());
		dto.setUrl(bean.getUrl());
		dto.setPublished(bean.isPublished());
		dto.setDescription(bean.getDescription());
		
		if (bean.getCategory()!= null){
			dto.setCategoryId(bean.getCategory().getId());
		}
		
		
		return dto;
	}
	
	public static Video DTOToBean(VideoDTO dto){
		Video bean = new Video();
		return DTOToBean(dto, bean);
	}
	
	public static Video DTOToBean(VideoDTO dto, Video bean){
		
		bean.setId(dto.getId());
		bean.setTitle(dto.getTitle());
		bean.setOrdering(dto.getOrdering());
		bean.setUrl(dto.getUrl());
		bean.setPublished(dto.isPublished());
		bean.setDescription(dto.getDescription());
		
		//Category
		Category category = new Category();
		category.setId(dto.getCategoryId());
		bean.setCategory(category);
		
		return bean;
	}
	
}
