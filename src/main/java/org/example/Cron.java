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
// need methods to parse the following:
// if starts with "*" meaning intervals up to total (say minutes which is 60 */15 would do intervals of how many times 15 can fit into 60 i.e 5 [0,15.30.45,60]
//  if standalone *, this means all the values that the expression can take, so month has 12, so 1,2,3,4,5,6 ..., 12
// if expression has "," dispaly only those indicated e.g 1,15 would express 1 15
// if has "-", return the values between, so 1-5 rturns 1,2,3,4,5

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
