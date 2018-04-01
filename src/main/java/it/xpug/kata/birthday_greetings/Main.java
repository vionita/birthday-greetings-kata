package it.xpug.kata.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException, MessagingException {
		BirthdayService service = new BirthdayService(new EmailGreetingsSender(new SmtpConfig("localhost", 25)));

		service.sendGreetings("employee_data.txt", new XDate());
	}

}
