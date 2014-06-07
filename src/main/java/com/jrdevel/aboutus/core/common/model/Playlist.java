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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Playlist generated by hbm2java
 */
@Entity
@Table(name = "tbl_playlists")
public class Playlist implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private String name;
	private Date createDate;
	private Date modificationDate;
	private Set<Music> musics = new HashSet<Music>(0);

	public Playlist() {
	}

	public Playlist(Customer customer, String name, Date createDate,
			Date modificationDate) {
		this.customer = customer;
		this.name = name;
		this.createDate = createDate;
		this.modificationDate = modificationDate;
	}

	public Playlist(Customer customer, String name, Date createDate,
			Date modificationDate, Set<Music> musics) {
		this.customer = customer;
		this.name = name;
		this.createDate = createDate;
		this.modificationDate = modificationDate;
		this.musics = musics;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	@Column(name = "modificationDate", nullable = false, length = 19)
	public Date getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_musics_playlist", joinColumns = { @JoinColumn(name = "id_playlist", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_music", nullable = false, updatable = false) })
	public Set<Music> getMusics() {
		return this.musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}

}
