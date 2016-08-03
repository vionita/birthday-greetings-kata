package it.xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException, MessagingException {
		BirthdayService service = new BirthdayService(new MessageSender(new SmtpConfig("localhost", 25)));

		service.sendGreetings("employee_data.txt", new XDate());
	}

}
