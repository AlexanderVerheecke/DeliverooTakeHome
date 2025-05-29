package org.example;

import lombok.Data;

@Data
public class Cron {
    private CronPart minutes;
    private CronPart hours;
    private CronPart dayOfMonth;
    private CronPart months;
    private CronPart dayOfWeek;

    private String command;

    public Cron (String arg) throws  IllegalArgumentException{
        String[] cronMembers = arg.split(" ");

        if (cronMembers.length != 6) {
            throw new IllegalArgumentException("Expected 6 arguments but got " + cronMembers.length + "; input: " + arg);
        }

        minutes = (new CronPart(cronMembers[0], CronPartType.MINUTES));
        hours = (new CronPart(cronMembers[1], CronPartType.HOURS));
        dayOfMonth = ( new CronPart(cronMembers[2], CronPartType.DAYOFMONTH));
        months = (new CronPart(cronMembers[3], CronPartType.MONTHS));
        dayOfWeek = (new CronPart(cronMembers[4], CronPartType.DAYOFWEEK));
        command = (cronMembers[5]);
    }

    public String printExression() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%-14s%s\n", "minute", minutes.getExpandedText()));
        result.append(String.format("%-14s%s\n", "hours", hours.getExpandedText()));
        result.append(String.format("%-14s%s\n", "day of month", dayOfMonth.getExpandedText()));
        result.append(String.format("%-14s%s\n", "month", months.getExpandedText()));
        result.append(String.format("%-14s%s\n", "day of week", dayOfWeek.getExpandedText()));
        result.append(String.format("%-14s%s", "command", command));

        System.out.println(result);
        return result.toString();
    }
}


// have a cron class
// have a method that will parse the cron expression
// need methods to parse the following:
// if  *, this means all the values that the expression can take, so month has 12, so 1,2,3,4,5,6 ..., 12
// if  "/" meaning intervals up to total (say minutes which is 60 */15 would do intervals of how many times 15 can fit into 60 i.e 5 [0,15.30.45,60]
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
