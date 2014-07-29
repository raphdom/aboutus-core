package com.jrdevel.aboutus.core.music.playlist;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.jrdevel.aboutus.core.common.model.Music;
import com.jrdevel.aboutus.core.common.model.Playlist;
import com.jrdevel.aboutus.core.music.music.MusicDTO;

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
		dto.setName(view.getName());
		dto.setCreateDate(view.getCreateDate());
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
	
	public static PlaylistDTO beanToDTO(Playlist bean){
		
		PlaylistDTO dto = new PlaylistDTO();
		
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		
		if (CollectionUtils.isNotEmpty(bean.getMusics())){
			List<MusicDTO> musicsDTO = new ArrayList<MusicDTO>();
			for (Music musicBean : bean.getMusics()){
				MusicDTO musicDTO = new MusicDTO();
				musicDTO.setId(musicBean.getId());
				musicDTO.setTitle(musicBean.getTitle());
				musicDTO.setLiryc(musicBean.getLiryc());
				musicsDTO.add(musicDTO);
			}
			dto.setMusics(musicsDTO);
		}
		
		return dto;
	}
	
	public static Playlist DTOToBean(PlaylistDTO dto){
		Playlist bean = new Playlist();
		return DTOToBean(dto, bean);
	}
	
	public static Playlist DTOToBean(PlaylistDTO dto, Playlist bean){
		
		bean.setId(dto.getId());
		bean.setName(dto.getName());
		
		return bean;
	}
	
}
