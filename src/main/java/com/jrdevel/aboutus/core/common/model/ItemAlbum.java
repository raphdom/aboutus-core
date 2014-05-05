package com.jrdevel.aboutus.core.common.model;

// Generated 5/mai/2014 23:56:03 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItemAlbum generated by hbm2java
 */
@Entity
@Table(name = "tbl_item_album")
public class ItemAlbum implements java.io.Serializable {

	private Integer id;
	private Album album;
	private Video video;
	private File file;
	private String title;
	private String description;
	private int position;

	public ItemAlbum() {
	}

	public ItemAlbum(Album album, int position) {
		this.album = album;
		this.position = position;
	}

	public ItemAlbum(Album album, Video video, File file, String title,
			String description, int position) {
		this.album = album;
		this.video = video;
		this.file = file;
		this.title = title;
		this.description = description;
		this.position = position;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "albumId", nullable = false)
	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videoId")
	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileId")
	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "position", nullable = false)
	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
