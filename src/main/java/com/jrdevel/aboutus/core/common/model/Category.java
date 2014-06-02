package com.jrdevel.aboutus.core.common.model;

// Generated 2/jun/2014 21:02:16 by Hibernate Tools 3.4.0.CR1

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

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "tbl_categories")
public class Category implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private File file;
	private String name;
	private String description;
	private int position;
	private int parent;
	private boolean publishedAlbuns;
	private boolean publishedVideos;
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Article> articles = new HashSet<Article>(0);
	private Set<Video> videos = new HashSet<Video>(0);
	private Set<Event> events = new HashSet<Event>(0);

	public Category() {
	}

	public Category(Customer customer, String name, int position, int parent,
			boolean publishedAlbuns, boolean publishedVideos) {
		this.customer = customer;
		this.name = name;
		this.position = position;
		this.parent = parent;
		this.publishedAlbuns = publishedAlbuns;
		this.publishedVideos = publishedVideos;
	}

	public Category(Customer customer, File file, String name,
			String description, int position, int parent,
			boolean publishedAlbuns, boolean publishedVideos,
			Set<Album> albums, Set<Article> articles, Set<Video> videos,
			Set<Event> events) {
		this.customer = customer;
		this.file = file;
		this.name = name;
		this.description = description;
		this.position = position;
		this.parent = parent;
		this.publishedAlbuns = publishedAlbuns;
		this.publishedVideos = publishedVideos;
		this.albums = albums;
		this.articles = articles;
		this.videos = videos;
		this.events = events;
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
	@JoinColumn(name = "thumb")
	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "parent", nullable = false)
	public int getParent() {
		return this.parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	@Column(name = "publishedAlbuns", nullable = false)
	public boolean isPublishedAlbuns() {
		return this.publishedAlbuns;
	}

	public void setPublishedAlbuns(boolean publishedAlbuns) {
		this.publishedAlbuns = publishedAlbuns;
	}

	@Column(name = "publishedVideos", nullable = false)
	public boolean isPublishedVideos() {
		return this.publishedVideos;
	}

	public void setPublishedVideos(boolean publishedVideos) {
		this.publishedVideos = publishedVideos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

}
