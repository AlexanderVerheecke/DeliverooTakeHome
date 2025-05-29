package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            CronParser cronParser = new CronParser();
            Cron cron = Cron.fromString("test */15 0 1,15 * 1-5 /usr/bin/find"); // invalid input
            cronParser.parse(cron);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}