package org.example;

public class Main {
    public static void main(String[] args) {

            CronParser cronParser = new CronParser();
            Cron cron = Cron.fromString("*/15 0 1,15 * 1-5 /usr/bin/find");
            cronParser.parse(cron);

    }
}