package it.xpug.kata.birthday_greetings;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageSender {

    public static final String SUBJECT = "Happy Birthday!";
    public static final String EMAIL_BODY_TEMPLATE = "Happy Birthday, dear %NAME%!";

    public void sendMessage(String smtpHost, int smtpPort, String email, String firstName) throws MessagingException {
        Session session = configureMailSession(smtpHost, smtpPort);
        Message msg = createMessage(session, email, firstName);
        Transport.send(msg);
    }

    private Message createMessage(Session session, String employeeEmail, String firstName) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("sender@here.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(employeeEmail));
        msg.setSubject(SUBJECT);
        msg.setText(body(firstName));
        return msg;
    }

    private Session configureMailSession(String smtpHost, int smtpPort) {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getInstance(props, null);
    }

    private String body(String firstName) {
        return EMAIL_BODY_TEMPLATE.replace("%NAME%", firstName);
    }

}
