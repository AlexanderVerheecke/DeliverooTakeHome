package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cron {
    private String minutes;
    private String hours;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;

    private String command;

    public static Cron fromString(String arg) {
        String[] cronMembers = arg.split(" ");

        if (cronMembers.length != 6) {
            throw new IllegalArgumentException("Expected 6 arguments but got " + cronMembers.length+  ": input : " + arg);
        }

        return Cron.builder()
                .minutes(cronMembers[0])
                .hours(cronMembers[1])
                .dayOfMonth(cronMembers[2])
                .month(cronMembers[3])
                .dayOfWeek(cronMembers[4])
                .command(cronMembers[5])
                .build();
    }

}


// have a cron class
// have a method that will parse the cron expression


// for the input argumetn:
//  */15 0 1,15 * 1-5 /usr/bin/find
//output should be:
// minute 0 15 30 45 60
// hour 0
// day of the month 1 15
// month 1 2 3 4 5 6 7 8 9 10 11 12
// day of the week 1 2 3 4 5
// command /usr/bin/find
//
//
//
//
//
//
