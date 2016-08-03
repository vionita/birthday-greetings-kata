package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BirthdayService {


	private GreetingsSender greetingsSender;

	public BirthdayService(GreetingsSender greetingsSender) {
		this.greetingsSender = greetingsSender;
	}

	public void sendGreetings(String fileName, XDate xDate) throws IOException, ParseException, AddressException, MessagingException {

		List<Employee> employees = new EmployeeRepository().getAllEmployees(fileName);

		for (Employee employee : employees){
			if (employee.isBirthday(xDate)) {
				greetingsSender.greet(employee);
			}
		}
	}

}
