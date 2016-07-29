package it.xpug.kata.birthday_greetings;

public class Recipient {
    private final String email;
    private final String firstName;

    public Recipient(String email, String firstName) {
        this.email = email;
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
}
