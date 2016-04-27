package com.twitter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class testsendmail {
    public static void main(String args[])  {

try{
        String host = "postmaster.local.twitter.com";
        String from = "sdhingra@twitter.com";
        String to = "sdhingra@twitter.com";

       System.out.println("********step********* ");
        // Get system properties
        Properties properties = System.getProperties();

       System.out.println("step 0");
        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        //properties.setProperty("mail.smtp.auth", "true"); 
       System.out.println("step 1");
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

         // Create a default MimeMessage object.
        System.out.println("step 2");
        MimeMessage message = new MimeMessage(session);

        // Set the RFC 822 "From" header field using the 
        // value of the InternetAddress.getLocalAddress method.
        message.setFrom(new InternetAddress(from));

        // Add the given addresses to the specified recipient type.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));


        // Set the "Subject" header field.
        message.setSubject("Testing:please ignore");
        
        // Sets the given String as this part's content,
        // with a MIME type of "text/plain".
        message.setText("Hi ......");
System.out.println("step 3");
         message.saveChanges();
        // Send message
        Transport.send(message);
System.out.println("step 4");

        System.out.println("Message Send.....");
   }catch(Exception e)
     {
       System.out.println(""+e);
      }
}
}

