package com.jrdevel.aboutus.core.music.music;

import java.util.ArrayList;
import java.util.List;

import com.jrdevel.aboutus.core.common.model.Music;

/**
 * @author Raphael Domingues
 *
 */
public class MusicMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static MusicListDTO viewToDTO(MusicListView view){
		MusicListDTO dto = new MusicListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setAuthor(view.getAuthor());
		if (view.getLiryc().length()>100){
			dto.setLiryc(view.getLiryc().substring(0, 100));
		}else{
			dto.setLiryc(view.getLiryc());
		}
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<MusicListDTO> listViewTolistDTO(List<MusicListView> views){
		List<MusicListDTO> dtos = new ArrayList<MusicListDTO>();
		for(MusicListView view : views){
			MusicListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	public static MusicDTO beanToDTO(Music bean){
		
		MusicDTO dto = new MusicDTO();
		
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
	
	public static Music DTOToBean(MusicDTO dto){
		Music bean = new Music();
		return DTOToBean(dto, bean);
	}
	
	public static Music DTOToBean(MusicDTO dto, Music bean){
		
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
