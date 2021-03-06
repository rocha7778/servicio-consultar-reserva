package com.rocha.app.reservas.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class SendEmail implements ISendEmail  {
	
	private   String FROM = "rocha7778@hotmail.com";
    private   String FROMNAME = "Rocha";
    private   String TO = "rocha7778@gmail.com";
    
    private final String SMTP_USERNAME = "AKIAYQYZ55DWGUXZGZHI";
    private final String SMTP_PASSWORD = "BOK5+jC4ryjy/D7yIMkE3oMXKi1zkbK6MVHdwZ2llTi3";
    
    private final String HOST = "email-smtp.us-east-1.amazonaws.com";
    
    private final int PORT = 587;
    
    private static final String SUBJECT = "Confirmacion Reserva";
    

	@Override
	public void senEmail(String from, String to, Long numeroReserva) throws Exception{

		String BODY = String.join(
	    	    System.getProperty("line.separator"),
	    	    "<h1>Reserva Hotel Rocha</h1>",
	    	    "<p> Es un gusto comunicarle que su reserva No" +numeroReserva + "</p>",
	    	    "<p> fue registrada con existo </p>"
	    	    
	    	);
		
		Properties props = System.getProperties();
		
		props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");
    	
    	Session session = Session.getDefaultInstance(props);
    	
    	
    	MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/html");
        
        //msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        Transport transport = session.getTransport();
        System.out.println("Sending...");
        
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
    	
        // Send the email.
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Email sent!");

		
	}

}
