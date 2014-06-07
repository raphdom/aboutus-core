package com.jrdevel.aboutus.core.music.music;

import java.io.Serializable;

import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public class MusicListDTO extends NodeDTO implements Serializable{

	private static final long serialVersionUID = 2513871739789525803L;
	
	private Integer id;
	private String title;
	private String author;
	private String liryc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLiryc() {
		return liryc;
	}
	public void setLiryc(String liryc) {
		this.liryc = liryc;
	}
	
}