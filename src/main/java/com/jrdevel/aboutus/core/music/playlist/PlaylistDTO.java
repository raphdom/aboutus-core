package com.jrdevel.aboutus.core.music.playlist;

import java.io.Serializable;
import java.util.List;

import com.jrdevel.aboutus.core.music.music.MusicDTO;

public class PlaylistDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String name;
	private List<MusicDTO> musics;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MusicDTO> getMusics() {
		return musics;
	}
	public void setMusics(List<MusicDTO> musics) {
		this.musics = musics;
	}
	
}
