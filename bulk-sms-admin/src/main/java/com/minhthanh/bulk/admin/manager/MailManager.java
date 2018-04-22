package com.minhthanh.bulk.admin.manager;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailManager {
	public static final String HOST = "minhthanhdevelopers@gmail.com";
	public static final String PASSWORD = "agileminhthanh";
	
	public static boolean sendEmailByToken(String email,String token){
		String content = vertificaitonHtml(token);
		return sendEmail(HOST,PASSWORD,email, content);
	}
	
	public static boolean sendEmailByContent(String email, String content){
		return sendEmail(HOST,PASSWORD,email, content);
	}
	
	public static boolean sendEmailByTokenAndPassword(String email, String token,String password){
		String content = forgotPasswordHtml(token, password);
		return sendEmail(HOST,PASSWORD,email, content);
	}
	

	public static boolean sendEmail(String host,String password, String destination, String content) {
		Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); // added this line
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", host);
	    props.put("mail.smtp.password", password);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);



	    Session session = Session.getInstance(props,null);
	    MimeMessage message = new MimeMessage(session);

	    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

	    // Create the email addresses involved
	    try {
	        InternetAddress from = new InternetAddress("username");
	        message.setSubject("Bulk SMS Account Vertification ");
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));

	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText("some text to send");

	        // Add the text part to the multipart
	        multipart.addBodyPart(messageBodyPart);

	        // Create the html part
	        messageBodyPart = new MimeBodyPart();
	        String htmlMessage =content;
	        messageBodyPart.setContent(htmlMessage, "text/html");


	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);

	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com",host, password);
	        System.out.println("Transport: "+transport.toString());
	        transport.sendMessage(message, message.getAllRecipients());
	        
	        return true;
	    } catch (AddressException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		return false;
	}
	
	
	private static String vertificaitonHtml(String token) {
		StringBuilder sb  = new StringBuilder("");
		sb.append("<h1>Please confirm your account by this link:</h1>");
		sb.append("<br/>");
		sb.append("http://localhost:8080/verification?token=");
		sb.append(token);
		return sb.toString();
	}
	
	private static String forgotPasswordHtml(String token,String password) {
		StringBuilder sb  = new StringBuilder("");
		sb.append("Your current password is: ");
		sb.append("<b>" + password + "</b>");
		sb.append("<h1> Click the link below to signin:</h1>");
		sb.append("<br/>");
		sb.append("http://localhost:8080/signintoken?token=");
		sb.append(token);
		return sb.toString();
	}
}
