package com.adaptionsoft.games.uglytrivia;

public class ReportMessagesToConsole implements ReportMessages {
    public ReportMessagesToConsole() {
    }

    @Override
    public void reportMessage(final String message) {
        System.out.println(message);
    }
}