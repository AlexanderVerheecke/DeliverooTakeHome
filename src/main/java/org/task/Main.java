package org.task;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("ERROR, no cron expression provided. Example cron expression to be given as parameter */15 0 1,15 * 1-5 /usr/bin/find:");
            return;
        }
        try {
            Cron cron =  new Cron(args[0]);
            cron.printExression();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}