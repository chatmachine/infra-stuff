import javax.mail.*;
import javax.mail.internet.*;

import java.security.Security;
import java.util.*;

public class testmailwithauth {
   
    String d_email = "sdhingra@twitter.com", d_password = "manya123",
            d_host = "postmaster.twitter.com", d_port = "465", m_to = "sdhingra@twitter.com",
            m_subject = "Testing", m_text = "Hey, this is the testing email.";

    public testmailwithauth() {
        System.setProperty("javax.net.debug", "ssl");
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

       

        try {
            SMTPAuthenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(m_to));
           
            Transport.send(msg);
        } catch (Throwable mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testmailwithauth blah = new testmailwithauth();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}
