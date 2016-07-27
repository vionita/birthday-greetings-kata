package it.xpug.kata.birthday_greetings;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.ParseException;

public class Employee {

	private XDate birthDate;
	private String lastName;
	private String firstName;
	private String email;

	public Employee(String firstName, String lastName, String birthDate, String email) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = new XDate(birthDate);
		this.email = email;
	}

	public boolean isBirthday(XDate today) {
		return today.isSameDay(birthDate);
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	
}
