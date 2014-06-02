package com.jrdevel.aboutus.core.common.helper;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Raphael Domingues
 *
 */
@Component
public class MessageHelper implements ApplicationContextAware{
	
	private static MessageSource messageSource;
	
	private static ApplicationContext applicationContext;
	
	public static String getMessage(MessageKeyEnum key){
		return getMessage(key,null);
	}
	
	public static String getMessage(MessageKeyEnum key, Object[] params){
		return getMessageSource().getMessage(key.getKey(), params, getCurrentLocale());
	}
	
	public static Locale getCurrentLocale(){
		return LocaleContextHolder.getLocale();
	}
	
	public static String genericErrorMessage(){
		return getMessage(MessageKeyEnum.GEN_ERROR);
	}
	
	public static MessageSource getMessageSource(){
		if (messageSource == null){
			messageSource = (MessageSource) applicationContext.getBean("messageSourceService");
		}
		return messageSource;
		
	}

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}

}
