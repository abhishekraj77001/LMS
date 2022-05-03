package mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

class Mailer {
	public static void sendOld(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
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
}

public class SendMail {
	public SendMail(String uname, String loginTime) {
		// from,password,to,subject,message
		String from, pass, to, sub, msg;
		from = "testinglms321@gmail.com";
		pass = "LMS"+"Test"+"123";
		to = new account.Credentials().getEmail();
		sub = "login notificaion!!";
		msg = "user: " + uname + " Login Time: " + loginTime;
		Mailer.send(from, pass, to, sub, msg);
		// change from, password and to
	}

	public SendMail(StringBuilder sb) {
		// from,password,to,subject,message
		String from, pass, to, sub, msg;
		from = "testinglms321@gmail.com";
		pass = "LMS"+"Test"+"123";
		to = new account.Credentials().getEmail();
		sub = "login notificaion!!";
		msg = new String(sb);
		Mailer.send(from, pass, to, sub, msg);
		// change from, password and to
	}
}