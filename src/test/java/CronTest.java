import org.example.Cron;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CronTest {

    @Test
    void invalidCronThrowsException() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // has 7 elements, test causing thrown exception
            Cron cron = new Cron("test */15 0 1,15 * 1-5 /usr/bin/find");
            cron.printExression();        });

        Assertions.assertEquals("Expected 6 arguments but got 7; input: test */15 0 1,15 * 1-5 /usr/bin/find", exception.getMessage());
    }

    @Test
    void noAssertionThrown() {
        Assertions.assertDoesNotThrow(() -> {
            Cron cron = new Cron("*/15 0 1,15 * 1-5 /usr/bin/find");
            cron.printExression();        });
    }

    @Test
    void expandsStars(){
        Cron cron = new Cron("* * * * * /usr/bin/find");
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
                "minute        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59",
                "hours         1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24",
                "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31",
                "month         1 2 3 4 5 6 7 8 9 10 11 12",
                "day of week   1 2 3 4 5 6 7",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void expandDashes(){
        Cron cron = new Cron("1-5 0-5 1-20 1-2 1-2 /usr/bin/find"); // -
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
               "minute        1 2 3 4 5",
                "hours         0 1 2 3 4 5",
                "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20",
                "month         1 2",
                "day of week   1 2",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void expandCommas(){
        Cron cron = new Cron("1,5 0,5 1,20 1,2 1,2 /usr/bin/find"); // -
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
                "minute        1 5",
               "hours         0 5",
                "day of month  1 20",
                "month         1 2",
                "day of week   1 2",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void expandInteger(){
        Cron cron = new Cron("1 0 1 3 4 /usr/bin/find"); // -
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
             "minute        1",
                "hours         0",
                "day of month  1",
                "month         3",
                "day of week   4",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void parseValidCron() {
        Cron cron = new Cron("*/15 0 1,15 * 1-5 /usr/bin/find");
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
                "minute        0 15 30 45",
                "hours         0",
                "day of month  1 15",
                "month         1 2 3 4 5 6 7 8 9 10 11 12",
                "day of week   1 2 3 4 5",
                "command       /usr/bin/find"

        );

        Assertions.assertEquals(expectedOutput, result);
    }
}