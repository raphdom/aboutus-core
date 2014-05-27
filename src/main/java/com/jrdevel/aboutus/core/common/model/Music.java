package com.jrdevel.aboutus.core.common.model;

// Generated 27/mai/2014 21:32:42 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Music generated by hbm2java
 */
@Entity
@Table(name = "tbl_musics")
public class Music implements java.io.Serializable {

	private Integer id;
	private Integer version;
	private Folder folder;
	private String title;
	private String liryc;
	private String seq;
	private String drumsStyle;
	private Integer time;
	private String originalTone;
	private String observations;
	private String link;
	private Date createDate;
	private Date modificationDate;
	private String author;
	private String musicNotes;
	private boolean favorite;
	private Set<Playlist> playlists = new HashSet<Playlist>(0);
	private Set<Tab> tabs = new HashSet<Tab>(0);

	public Music() {
	}

	public Music(String title, String liryc, Date createDate, boolean favorite) {
		this.title = title;
		this.liryc = liryc;
		this.createDate = createDate;
		this.favorite = favorite;
	}

	public Music(Folder folder, String title, String liryc, String seq,
			String drumsStyle, Integer time, String originalTone,
			String observations, String link, Date createDate,
			Date modificationDate, String author, String musicNotes,
			boolean favorite, Set<Playlist> playlists, Set<Tab> tabs) {
		this.folder = folder;
		this.title = title;
		this.liryc = liryc;
		this.seq = seq;
		this.drumsStyle = drumsStyle;
		this.time = time;
		this.originalTone = originalTone;
		this.observations = observations;
		this.link = link;
		this.createDate = createDate;
		this.modificationDate = modificationDate;
		this.author = author;
		this.musicNotes = musicNotes;
		this.favorite = favorite;
		this.playlists = playlists;
		this.tabs = tabs;
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

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folderId")
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "liryc", nullable = false, length = 65535)
	public String getLiryc() {
		return this.liryc;
	}

	public void setLiryc(String liryc) {
		this.liryc = liryc;
	}

	@Column(name = "seq", length = 100)
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Column(name = "drumsStyle", length = 100)
	public String getDrumsStyle() {
		return this.drumsStyle;
	}

	public void setDrumsStyle(String drumsStyle) {
		this.drumsStyle = drumsStyle;
	}

	@Column(name = "time")
	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Column(name = "originalTone", length = 2)
	public String getOriginalTone() {
		return this.originalTone;
	}

	public void setOriginalTone(String originalTone) {
		this.originalTone = originalTone;
	}

	@Column(name = "observations", length = 65535)
	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Column(name = "link", length = 65535)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modificationDate", length = 19)
	public Date getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	@Column(name = "author", length = 100)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "musicNotes", length = 65535)
	public String getMusicNotes() {
		return this.musicNotes;
	}

	public void setMusicNotes(String musicNotes) {
		this.musicNotes = musicNotes;
	}

	@Column(name = "favorite", nullable = false)
	public boolean isFavorite() {
		return this.favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_musics_playlist", joinColumns = { @JoinColumn(name = "id_music", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_playlist", nullable = false, updatable = false) })
	public Set<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "music")
	public Set<Tab> getTabs() {
		return this.tabs;
	}

	public void setTabs(Set<Tab> tabs) {
		this.tabs = tabs;
	}

}
