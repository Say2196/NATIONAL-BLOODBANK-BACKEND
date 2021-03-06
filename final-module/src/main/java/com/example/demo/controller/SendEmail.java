package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/approve")
public class SendEmail {

//	http://localhost:8080/approve/send/sayantan.jana.712@gmail.com/hello
	  @GetMapping("send/{to}/{otp}")			//http://....../"+to+"/"+otp
	  public void sendEmail(@PathVariable("to") String to,@PathVariable("otp") String otp)
	    {
		  System.out.println(to);
		  System.out.println(otp);
	   	 Properties props = new Properties();
	     props.put("mail.smtp.host", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.host", "smtp.gmail.com");
	     props.put("mail.smtp.port", "587");
	     props.put("mail.smtp.auth", "true");
	     //Establishing a session with required user details
	     Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication("abhi.demolearning@gmail.com", "Password@123");
	         }
	     });
	     try {
	         //Creating a Message object to set the email content
	         MimeMessage msg = new MimeMessage(session);
	         //Storing the comma seperated values to email addresses
//	         String to = "rjagadeeswaran@yahoo.com";
	         /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
	         addresses in an array of InternetAddress objects*/
	         InternetAddress[] address = InternetAddress.parse(to, true);
	         //Setting the recepients from the address variable
	         msg.setRecipients(Message.RecipientType.TO, address);
	         String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
	         msg.setSubject("National Blood Bank: " + timeStamp);
	         msg.setSentDate(new Date());
//	         msg.setText("Sampel System Generated mail");
	         msg.setText(otp);
	         msg.setHeader("XPriority", "1");
	        
	         Transport.send(msg);
	         System.out.println("Mail has been sent successfully");
	     } catch (MessagingException mex) {
	         System.out.println("Unable to send an email:\n" + mex);
	     }
	    }
	
}
