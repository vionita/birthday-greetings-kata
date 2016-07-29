package it.xpug.kata.birthday_greetings;

public class SmtpConfig {
    private final String smtpHost;
    private final int smtpPort;

    public SmtpConfig(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }
}
