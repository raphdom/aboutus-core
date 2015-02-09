package com.jrdevel.aboutus.core.common.helper;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.jrdevel.aboutus.core.common.configuration.AboutUsConfiguration;

/**
 * @author Raphael Domingues
 *
 */
@Component
public class EmailSender {

	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private AboutUsConfiguration config;
	
	public void sendEmail(String subject, String template, String destinatary, Object... params){
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.mandrillapp.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(config.getEmailUser(), config.getEmailPass());
			}
		});

		try {

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(config.getSender()));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatary));
			msg.setSubject(subject);
			
			HashMap<String,Object> model = new HashMap<String,Object>();
			int i = 1;
			for(Object param: params){
				model.put("param"+i, param);
				i++;
			}
            
            String body = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, template, "UTF-8", model);
            msg.setContent(body, "text/html");
			
			Transport.send(msg);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
