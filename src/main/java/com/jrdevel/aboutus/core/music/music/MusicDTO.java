package com.jrdevel.aboutus.core.music.music;

import java.io.Serializable;

public class MusicDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String title;
	private String drumsStyle;
	private Integer time;
	private String originalTone;
	private String link;
	private String author;
	private String musicNotes;
	private String liryc;
	private String content;
	private String observations;
	private boolean favorite;
	
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
	public String getDrumsStyle() {
		return drumsStyle;
	}
	public void setDrumsStyle(String drumsStyle) {
		this.drumsStyle = drumsStyle;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getOriginalTone() {
		return originalTone;
	}
	public void setOriginalTone(String originalTone) {
		this.originalTone = originalTone;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMusicNotes() {
		return musicNotes;
	}
	public void setMusicNotes(String musicNotes) {
		this.musicNotes = musicNotes;
	}
	public String getLiryc() {
		return liryc;
	}
	public void setLiryc(String liryc) {
		this.liryc = liryc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
}
