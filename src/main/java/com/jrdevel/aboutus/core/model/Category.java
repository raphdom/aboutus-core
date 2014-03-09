package com.jrdevel.aboutus.core.model;

// Generated 9/Mar/2014 21:10:27 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private Integer id;
	private File file;
	private String name;
	private String description;
	private int position;
	private int parent;
	private boolean publishedAlbuns;
	private boolean publishedVideos;
	private Set<Event> events = new HashSet<Event>(0);
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Article> articles = new HashSet<Article>(0);
	private Set<Video> videos = new HashSet<Video>(0);

	public Category() {
	}

	public Category(String name, int position, int parent,
			boolean publishedAlbuns, boolean publishedVideos) {
		this.name = name;
		this.position = position;
		this.parent = parent;
		this.publishedAlbuns = publishedAlbuns;
		this.publishedVideos = publishedVideos;
	}

	public Category(File file, String name, String description, int position,
			int parent, boolean publishedAlbuns, boolean publishedVideos,
			Set<Event> events, Set<Album> albums, Set<Article> articles,
			Set<Video> videos) {
		this.file = file;
		this.name = name;
		this.description = description;
		this.position = position;
		this.parent = parent;
		this.publishedAlbuns = publishedAlbuns;
		this.publishedVideos = publishedVideos;
		this.events = events;
		this.albums = albums;
		this.articles = articles;
		this.videos = videos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getParent() {
		return this.parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public boolean isPublishedAlbuns() {
		return this.publishedAlbuns;
	}

	public void setPublishedAlbuns(boolean publishedAlbuns) {
		this.publishedAlbuns = publishedAlbuns;
	}

	public boolean isPublishedVideos() {
		return this.publishedVideos;
	}

	public void setPublishedVideos(boolean publishedVideos) {
		this.publishedVideos = publishedVideos;
	}

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

}
