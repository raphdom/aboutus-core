package com.jrdevel.aboutus.core.cloud;

public enum ImageSizeEnum {
	
	DATA_TYPE_1(50,50,1),
	DATA_TYPE_2(80,80,2),
	DATA_TYPE_3(100,100,3),
	DATA_TYPE_4(150,150,4),
	DATA_TYPE_5(280,200,5),
	DATA_TYPE_6(300,300,6),
	DATA_TYPE_7(400,400,7),
	DATA_TYPE_8(500,500,8),
	DATA_TYPE_9(720,480,9),
	DATA_TYPE_10(1920,1080,10);
	
	private Integer datatype = null;
	private Integer width = null;
	private Integer height = null;
	
	public static ImageSizeEnum getImageSizeByDatatype(Integer datatype){
	    for(ImageSizeEnum imageSize : ImageSizeEnum.values()){
	        if(imageSize.getDatatype() == datatype)
	            return imageSize;
	    }
	    return null;
	}
	
	private ImageSizeEnum(Integer width, Integer height, Integer datatype){
		this.datatype=datatype;
		this.width=width;
		this.height=height;
	}

	public Integer getDatatype() {
		return datatype;
	}
	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

}
