package otp;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailOTP {
	public static void sendOld(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		
        props.put("mail.smtp.starttls.enable", "true"); //TLS
        props.put("mail.transport.protocol", "smtp");
        
//		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.port", "587");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			
//			message.setFrom(from);
			
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void send(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		
//		props.put("mail.smtp.username", from);
//		prop.put("mail.smtp.password", password);
		
		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.port", "587");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		
        props.put("mail.smtp.starttls.enable", "true"); //TLS
        props.put("mail.transport.protocol", "smtp");
        
//		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.port", "587");
		
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); 
		
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			
			message.setFrom(from);
			message.setSentDate(new Date());
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
//			throw new RuntimeException(e);
			System.out.println(e);		}
	}

	public MailOTP(String to, int otp) {
		// from,password,to,subject,message
		String from, pass, sub, msg;
		from = "testinglms321@gmail.com";
		pass = "LMSTest123";
		sub = "OTP for installation";
		msg = "Your OTP for installing Login Monitor Software: " + otp;
		MailOTP.send(from, pass, to, sub, msg);
		// change from, password and to
	}
	
	public static void main(String args[]) {
		new MailOTP("codewiretesting123@gmail.com", 45656);
	}
}
