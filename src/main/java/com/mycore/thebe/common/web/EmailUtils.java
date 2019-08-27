package com.mycore.thebe.common.web;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.mycore.thebe.common.data.EmailAssistant;

/**
 * Class that concern in email process
 * @author Thebe.Alfarisi
 * @since Dec, 27th 2018
 * @version 1.0
 *
 */
public class EmailUtils {

	/**
	 * Method to create and send simple email
	 * @param content Email content
	 * @param subject Email subject
	 * @param username username authenticator
	 * @param password password authenticator
	 * @param sender Sender email
	 * @param destination {@link List} of email destination
	 * @param carbonCopy {@link List} of email carbon copy
	 * @param backCarbonCopy {@link List} of email back carbon copy
	 * @param host email host
	 * @param port email port
	 * @throws Exception 
	 */
	public static void sendSimpleEmail(String content, String subject, String username, String password, String sender, List<String> destination, List<String> carbonCopy, List<String> backCarbonCopy, String host, String port) throws Exception {
		try {
			Email email = new SimpleEmail();
			email.setHostName(host);
			email.setSmtpPort(Integer.valueOf(port));
			email.setAuthenticator(new DefaultAuthenticator(username, password));
			email.setSSL(true);
			email.setFrom(sender);
			email.setSubject(subject);
			email.setMsg(content);
			
			if (destination != null) {
				for (String dest : destination) {
					email.addTo(dest);
				}
			}
			
			if (carbonCopy != null) {
				for (String cc : carbonCopy) {
					email.addCc(cc);
				}
			}
			
			if (backCarbonCopy != null) {
				for (String bcc : backCarbonCopy) {
					email.addBcc(bcc);
				}
			}
			
			email.send();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Method to create and send HTML email format
	 * @param content Email content
	 * @param subject Email subject
	 * @param username username authenticator
	 * @param password password authenticator
	 * @param sender Sender email
	 * @param destination {@link List} of email destination
	 * @param carbonCopy {@link List} of email carbon copy
	 * @param backCarbonCopy {@link List} of email back carbon copy
	 * @param host email host
	 * @param port email port
	 * @throws Exception 
	 */
	public static void sendHTMLEmail(String content, String subject, String username, String password, String sender, List<String> destination, List<String> carbonCopy, List<String> backCarbonCopy, String host, String port) throws Exception {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(host);
			email.setSmtpPort(Integer.valueOf(port));
			email.setAuthenticator(new DefaultAuthenticator(username, password));
			email.setTLS(false);
			email.setFrom(sender);
			email.setSubject(subject);
			email.setHtmlMsg(content);
			
			if (destination != null) {
				for (String dest : destination) {
					email.addTo(dest);
				}
			}
			
			if (carbonCopy != null) {
				for (String cc : carbonCopy) {
					email.addCc(cc);
				}
			}
			
			if (backCarbonCopy != null) {
				for (String bcc : backCarbonCopy) {
					email.addBcc(bcc);
				}
			}
			
			email.send();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Method to create and send attachment email
	 * @param content Email content
	 * @param subject Email subject
	 * @param username username authenticator
	 * @param password password authenticator
	 * @param sender Sender email
	 * @param destination {@link List} of email destination
	 * @param carbonCopy {@link List} of email carbon copy
	 * @param backCarbonCopy {@link List} of email back carbon copy
	 * @param host email host
	 * @param port email port
	 * @param emailAssistants {@link List} of {@link EmailAssistant} to help attachment process
	 * @throws Exception 
	 */
	public static void sendAttachmentEmail(String content, String subject, String username, String password, String sender, List<String> destination, List<String> carbonCopy, List<String> backCarbonCopy, String host, String port, List<EmailAssistant> emailAssistants) throws Exception {
		try {
			
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(host);
			email.setSmtpPort(Integer.valueOf(port));
			email.setAuthenticator(new DefaultAuthenticator(username, password));
			email.setSSL(true);
			email.setFrom(sender);
			email.setSubject(subject);
			email.setMsg(content);
			
			if (destination != null) {
				for (String dest : destination) {
					email.addTo(dest);
				}
			}
			
			if (carbonCopy != null) {
				for (String cc : carbonCopy) {
					email.addCc(cc);
				}
			}
			
			if (backCarbonCopy != null) {
				for (String bcc : backCarbonCopy) {
					email.addBcc(bcc);
				}
			}
			
			for(EmailAssistant emailAssistant : emailAssistants) {
				ByteArrayInputStream bais = new ByteArrayInputStream(emailAssistant.getFilebytes());
				ByteArrayDataSource source = new ByteArrayDataSource(bais, emailAssistant.getFileType());
				
				email.attach(source, emailAssistant.getFileName(), null);
				
			}
			
			
			email.send();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
