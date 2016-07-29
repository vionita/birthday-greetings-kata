package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BirthdayService {



	public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {

		List<Employee> employees = getAllEmployees(fileName);

		for (Employee employee : employees){
			if (employee.isBirthday(xDate)) {
				new MessageSender().sendMessage(smtpHost, smtpPort, employee.getEmail(), employee.getFirstName());
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


}
