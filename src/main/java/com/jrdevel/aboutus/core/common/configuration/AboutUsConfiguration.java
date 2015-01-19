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
	@Value("#{applicationProperties.sender}")
	private String sender;
	
	@Value("#{applicationProperties.refreshToken}")
	private String refreshToken; 
	@Value("#{applicationProperties.clientId}")
	private String clientId; 
	@Value("#{applicationProperties.clientSecret}")
	private String clientSecret; 
	
	@Value("#{applicationProperties.uniqueCustomer}")
	private boolean uniqueCustomer;
	
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
	public String getRefreshToken() {
		return refreshToken;
	}
	public String getClientId() {
		return clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public boolean isUniqueCustomer() {
		return uniqueCustomer;
	}
	public void setUniqueCustomer(boolean uniqueCustomer) {
		this.uniqueCustomer = uniqueCustomer;
	}

}
