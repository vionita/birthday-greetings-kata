package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

	public static final String SUBJECT = "Happy Birthday!";
	public static final String EMAIL_BODY_TEMPLATE = "Happy Birthday, dear %NAME%!";

	public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {

		List<Employee> employees = getAllEmployees(fileName);

		for (Employee employee : employees){
			if (employee.isBirthday(xDate)) {
				sendMessage(smtpHost, smtpPort, employee.getEmail(), employee.getFirstName());
			}
		}
	}

	private List<Employee> getAllEmployees(String fileName) throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = "";
		str = in.readLine(); // skip header
		List<Employee> employees = new ArrayList<>();
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);

			employees.add(employee);
		}
		return employees;
	}

	protected void sendMessage(String smtpHost, int smtpPort, String email, String firstName) throws MessagingException {
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
