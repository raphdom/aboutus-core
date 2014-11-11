package com.jrdevel.aboutus.core.common.helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.jrdevel.aboutus.core.common.configuration.AboutUsConfiguration;

/**
 * @author Raphael Domingues
 *
 */
@Component
public class EmailHelper implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	private static AboutUsConfiguration config;

	public static void sendEmail(String subject, String message, String destinatary){
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.mandrillapp.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getConfig().getEmailUser(), getConfig().getEmailPass());
			}
		});

		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(getConfig().getSender()));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatary));
			msg.setSubject(subject);
			msg.setText(message);

			Transport.send(msg);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	
	public static AboutUsConfiguration getConfig(){
		if (config == null){
			config = (AboutUsConfiguration) applicationContext.getBean("aboutUsConfiguration");
		}
		return config;
		
	}
	
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}

}
