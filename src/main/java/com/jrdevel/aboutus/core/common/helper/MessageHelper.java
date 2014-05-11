package com.jrdevel.aboutus.core.common.helper;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Raphael Domingues
 *
 */
@Component
public class MessageHelper {
	
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(MessageKeyEnum key){
		return getMessage(key,null);
	}
	
	public String getMessage(MessageKeyEnum key, Object[] params){
		return messageSource.getMessage(key.getKey(), params, getCurrentLocale());
	}
	
	public Locale getCurrentLocale(){
		return LocaleContextHolder.getLocale();
	}
	
	public String genericErrorMessage(){
		return getMessage(MessageKeyEnum.GEN_ERROR);
	}

}
