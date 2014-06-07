package com.jrdevel.aboutus.core.common.model;

// Generated 7/jun/2014 21:22:28 by Hibernate Tools 3.4.0.CR1

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * File generated by hbm2java
 */
@Entity
@Table(name = "tbl_file")
public class File implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private Folder folder;
	private String filename;
	private String fileType;
	private long filesize;
	private String title;
	private String caption;
	private String keywords;
	private boolean published;
	private String path;
	private Date createdDate;
	private Date modifiedDate;
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<FileData> fileDatas = new HashSet<FileData>(0);
	private Set<ItemAlbum> itemAlbums = new HashSet<ItemAlbum>(0);
	private Set<Event> events = new HashSet<Event>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<Video> videos = new HashSet<Video>(0);
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Church> churches = new HashSet<Church>(0);
	private Set<Banner> banners = new HashSet<Banner>(0);
	private Set<Article> articles = new HashSet<Article>(0);

	public File() {
	}

	public File(Customer customer, Folder folder, String filename,
			String fileType, long filesize, String title, boolean published,
			String path, Date createdDate, Date modifiedDate) {
		this.customer = customer;
		this.folder = folder;
		this.filename = filename;
		this.fileType = fileType;
		this.filesize = filesize;
		this.title = title;
		this.published = published;
		this.path = path;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public File(Customer customer, Folder folder, String filename,
			String fileType, long filesize, String title, String caption,
			String keywords, boolean published, String path, Date createdDate,
			Date modifiedDate, Set<Album> albums, Set<FileData> fileDatas,
			Set<ItemAlbum> itemAlbums, Set<Event> events, Set<User> users,
			Set<Video> videos, Set<Category> categories, Set<Church> churches,
			Set<Banner> banners, Set<Article> articles) {
		this.customer = customer;
		this.folder = folder;
		this.filename = filename;
		this.fileType = fileType;
		this.filesize = filesize;
		this.title = title;
		this.caption = caption;
		this.keywords = keywords;
		this.published = published;
		this.path = path;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.albums = albums;
		this.fileDatas = fileDatas;
		this.itemAlbums = itemAlbums;
		this.events = events;
		this.users = users;
		this.videos = videos;
		this.categories = categories;
		this.churches = churches;
		this.banners = banners;
		this.articles = articles;
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
	@JoinColumn(name = "customerId", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folderId", nullable = false)
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Column(name = "filename", nullable = false)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "fileType", nullable = false, length = 100)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "filesize", nullable = false)
	public long getFilesize() {
		return this.filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "caption", length = 65535)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "keywords", length = 65535)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "published", nullable = false)
	public boolean isPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Column(name = "path", nullable = false)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdDate", nullable = false, length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedDate", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<FileData> getFileDatas() {
		return this.fileDatas;
	}

	public void setFileDatas(Set<FileData> fileDatas) {
		this.fileDatas = fileDatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<ItemAlbum> getItemAlbums() {
		return this.itemAlbums;
	}

	public void setItemAlbums(Set<ItemAlbum> itemAlbums) {
		this.itemAlbums = itemAlbums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Church> getChurches() {
		return this.churches;
	}

	public void setChurches(Set<Church> churches) {
		this.churches = churches;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Banner> getBanners() {
		return this.banners;
	}

	public void setBanners(Set<Banner> banners) {
		this.banners = banners;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

}
