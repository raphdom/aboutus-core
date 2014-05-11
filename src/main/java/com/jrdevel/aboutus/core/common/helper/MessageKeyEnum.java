package com.jrdevel.aboutus.core.common.helper;

/**
 * @author Raphael Domingues
 *
 */
public enum MessageKeyEnum {
	
	GEN_ERROR ("gen.message.error"),
	
	DULICATED_EMAIL ("user.message.emailDuplicated");
	
	private String key = null;
	
	private MessageKeyEnum(String key){
		this.key=key;
	}
	
	public String getKey(){
		return key;
	}

}
