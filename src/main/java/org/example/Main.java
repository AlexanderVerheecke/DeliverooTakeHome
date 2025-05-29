package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Cron cron =  new Cron("*/15 0 1,15 * 1-5 /usr/bin/find");
            cron.printExression();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}