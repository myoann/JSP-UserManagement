package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Florian Massa and Yoann Moise
 */
public class SendEmail {

    public static boolean run(String addressedTo, String subject, String content) {

        String fromAddress = addressedTo.trim();
        String recipients = "YOUREMAIL"; //Your Email Address//
        String contentType = "text/plain";

        String smtpHost = "smtp.gmail.com"; //Your Outgoing Mailbox//
        int smtpPort = 587;
        String username = "YOUREMAIL";//Your Mailbox Username//
        String password = "PASSWORD";//Your Mailbox Password//

        try
        {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(recipients, false));

            message.setSubject(subject);
            message.setContent(content, contentType);
            message.setSentDate(new Date());

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpPort, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (MessagingException messagingException)
        {
            System.out.print(messagingException);
            return false;

        } catch (Exception e)
        {
            System.out.print(e);
            return false;
        }
    }
}