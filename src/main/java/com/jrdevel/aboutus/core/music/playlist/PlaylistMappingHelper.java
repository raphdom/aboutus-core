package com.jrdevel.aboutus.core.music.playlist;

import java.util.ArrayList;
import java.util.List;

import com.jrdevel.aboutus.core.common.model.Music;

/**
 * @author Raphael Domingues
 *
 */
public class PlaylistMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static PlaylistListDTO viewToDTO(PlaylistListView view){
		PlaylistListDTO dto = new PlaylistListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setAuthor(view.getAuthor());
		dto.setLiryc(view.getLiryc().substring(0, 100));
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<PlaylistListDTO> listViewTolistDTO(List<PlaylistListView> views){
		List<PlaylistListDTO> dtos = new ArrayList<PlaylistListDTO>();
		for(PlaylistListView view : views){
			PlaylistListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	public static PlaylistDTO beanToDTO(Music bean){
		
		PlaylistDTO dto = new PlaylistDTO();
		
		dto.setId(bean.getId());
		dto.setTitle(bean.getTitle());
		dto.setTime(bean.getTime());
		dto.setOriginalTone(bean.getOriginalTone());
		dto.setDrumsStyle(bean.getDrumsStyle());
		dto.setLink(bean.getLink());
		dto.setAuthor(bean.getAuthor());
		dto.setMusicNotes(bean.getMusicNotes());
		dto.setFavorite(bean.isFavorite());
		dto.setLiryc(bean.getLiryc());
		//dto.setContent(bean.getTabs().);
		dto.setObservations(bean.getObservations());
		
		return dto;
	}
	
	public static Music DTOToBean(PlaylistDTO dto){
		Music bean = new Music();
		return DTOToBean(dto, bean);
	}
	
	public static Music DTOToBean(PlaylistDTO dto, Music bean){
		
		bean.setId(dto.getId());
		bean.setTitle(dto.getTitle());
		bean.setTime(dto.getTime());
		bean.setOriginalTone(dto.getOriginalTone());
		bean.setDrumsStyle(dto.getDrumsStyle());
		bean.setLink(dto.getLink());
		bean.setAuthor(dto.getAuthor());
		bean.setMusicNotes(dto.getMusicNotes());
		bean.setFavorite(dto.isFavorite());
		bean.setLiryc(dto.getLiryc());
		//bean.setContent(bean.getTabs().);
		bean.setObservations(dto.getObservations());
		
		return bean;
	}
	
}
