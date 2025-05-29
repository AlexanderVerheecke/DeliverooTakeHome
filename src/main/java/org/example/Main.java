package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Cron cron = Cron.fromString("* * * * * /usr/bin/find"); // invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}