package it.xpug.kata.birthday_greetings;

import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BirthdayServiceTest {


	private BirthdayService birthdayService;

	private TestGreetingSender greetingSenderStub;

	@Before
	public void setUp() throws Exception {
		greetingSenderStub = new TestGreetingSender();
		birthdayService = new BirthdayService(greetingSenderStub);
	}


	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"));

		assertEquals("john.doe@foobar.com", greetingSenderStub.greetedEmployee.getEmail());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings("employee_data.txt", new XDate("2008/01/01"));

		assertNull(greetingSenderStub.greetedEmployee);
	}

	private static class TestGreetingSender implements GreetingsSender{

		Employee greetedEmployee;

		@Override
		public void greet(Employee employee) throws MessagingException {
			this.greetedEmployee = employee;
		}
	}

}
