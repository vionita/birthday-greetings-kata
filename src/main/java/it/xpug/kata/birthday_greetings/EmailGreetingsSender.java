package it.xpug.kata.birthday_greetings;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGreetingsSender implements GreetingsSender {

    public static final String EMAIL_SENDER = "sender@here.com";
    public static final String EMAIL_SUBJECT = "Happy Birthday!";
    public static final String EMAIL_BODY_TEMPLATE = "Happy Birthday, dear %NAME%!";

    private SmtpConfig smtpConfig;

    public EmailGreetingsSender(SmtpConfig smtpConfig) {
        this.smtpConfig = smtpConfig;
    }


    @Override
    public void greet(Employee employee) throws MessagingException {
        Session session = configureMailSession(smtpConfig.getHost(), smtpConfig.getPort());
        Message msg = createMessage(session, employee.getEmail(), body(employee.getFirstName()));
        Transport.send(msg);
    }

    private Message createMessage(Session session, String recipientEmail, String body) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(EMAIL_SENDER));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        msg.setSubject(EMAIL_SUBJECT);
        msg.setText(body);
        return msg;
    }

    private Session configureMailSession(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getInstance(props, null);
    }

    private String body(String firstName) {
        return EMAIL_BODY_TEMPLATE.replace("%NAME%", firstName);
    }

}
