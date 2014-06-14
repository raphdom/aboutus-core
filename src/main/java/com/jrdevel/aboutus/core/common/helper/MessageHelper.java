package com.jrdevel.aboutus.core.common.helper;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
	
	private static final Logger logger = Logger.getLogger(MessageHelper.class);
	
	public static String getMessage(MessageKeyEnum key){
		return getMessage(key,null);
	}
	
	public static String getMessage(String key){
		return getMessage(key,null);
	}
	
	public static String getMessage(MessageKeyEnum key, Object[] params){
		return getMessageSource().getMessage(key.getKey(), params, getCurrentLocale());
	}
	
	public static String getMessage(String key, Object[] params){
		try{
			return getMessageSource().getMessage(key, params, getCurrentLocale());
		}catch(NoSuchMessageException e){
			logger.info("An error ocurred when get message for key=" + key);
		}
		
		return null;
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
