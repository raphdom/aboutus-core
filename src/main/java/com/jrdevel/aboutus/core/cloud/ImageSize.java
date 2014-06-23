package com.jrdevel.aboutus.core.cloud;

/**
 * @author Raphael
 *
 */
public class ImageSize {
	
	private Integer width;
	private Integer height;
	private Integer dataType;
	private boolean exactlySize;
	
	public ImageSize(){
		
	}
	
	public ImageSize(Integer width, Integer height) {
		this.width = width;
		this.height = height;
	}
	
	public ImageSize(Integer width, Integer height, Integer dataType) {
		this.width = width;
		this.height = height;
		this.dataType = dataType;
	}
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public boolean isExactlySize() {
		return exactlySize;
	}
	public void setExactlySize(boolean exactlySize) {
		this.exactlySize = exactlySize;
	}
	
	
}
