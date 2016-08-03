package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;

public interface GreetingsSender {

    void greet(Employee employee) throws MessagingException;

}
