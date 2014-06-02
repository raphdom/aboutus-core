package com.jrdevel.aboutus.core.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AboutUsConfiguration {
	

	@Value("#{applicationProperties.mediaPath}")
	private String mediaPath; 
	@Value("#{applicationProperties.emailUser}")
	private String emailUser; 
	@Value("#{applicationProperties.emailPass}")
	private String emailPass; 
	
	private AboutUsConfiguration() {
	}

	public String getMediaPath() {
		return mediaPath;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public String getEmailPass() {
		return emailPass;
	}

}
