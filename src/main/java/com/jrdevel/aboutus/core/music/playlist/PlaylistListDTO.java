package com.jrdevel.aboutus.core.music.playlist;

import java.io.Serializable;
import java.util.Date;

import net.aboutchurch.common.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public class PlaylistListDTO extends NodeDTO implements Serializable{

	private static final long serialVersionUID = 2513871739789525803L;
	
	private Integer id;
	private String name;
	private Date createDate;
	
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}