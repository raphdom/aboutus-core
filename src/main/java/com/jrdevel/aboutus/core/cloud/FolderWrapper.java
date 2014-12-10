package com.jrdevel.aboutus.core.cloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raphael Domingues
 *
 */
public class FolderWrapper implements Serializable{
	
	private static final long serialVersionUID = 5058736287655478100L;
	
	private Integer id;
	private String text;
	private Integer parent;
	private boolean leaf = true;
	private String path;

	private List<FolderWrapper> children = new ArrayList<FolderWrapper>();
	
	public FolderWrapper(){
		
	}
	
	public FolderWrapper(Integer id, String path, String text){
		this.id=id;
		this.path=path;
		this.text=text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public void addChild(FolderWrapper child) {
		children.add(child);
	}
	
	public List<FolderWrapper> getChildren() {
		return children;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
