package com.adaptionsoft.games.uglytrivia;

public class ReportMessagesToConsole implements ReportMessages {
    @Override
    public void reportMessage(final String message) {
        System.out.println(message);
    }
}