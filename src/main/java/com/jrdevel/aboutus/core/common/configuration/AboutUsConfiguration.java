package com.jrdevel.aboutus.core.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AboutUsConfiguration {
	

	@Value("#{applicationProperties.mediaPath}")
	private String mediaPath; 
	
	private AboutUsConfiguration() {
	}

	public String getMediaPath() {
		return mediaPath;
	}

}
