import org.task.Cron;
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
                "hour          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23",
                "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31",
                "month         1 2 3 4 5 6 7 8 9 10 11 12",
                "day of week   0 1 2 3 4 5 6",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void expandIntervals(){
        Cron cron = new Cron("*/10 */5 */4 */6 */3 /usr/bin/find"); // -
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
                "minute        0 10 20 30 40 50",
                "hour          0 5 10 15 20",
                "day of month  1 5 9 13 17 21 25 29",
                "month         1 7",
                "day of week   0 3 6",
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
                "hour          0 1 2 3 4 5",
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
               "hour          0 5",
                "day of month  1 20",
                "month         1 2",
                "day of week   1 2",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void parseInteger(){
        Cron cron = new Cron("1 0 1 3 4 /usr/bin/find"); // -
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
             "minute        1",
                "hour          0",
                "day of month  1",
                "month         3",
                "day of week   4",
                "command       /usr/bin/find"
        );

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void parseValidCron() {
        Cron cron = new Cron("*/15 0 1,5-10 * 1-5 /usr/bin/find");
        String result = cron.printExression();

        String expectedOutput = String.join("\n",
                "minute        0 15 30 45",
                "hour          0",
                "day of month  1 5 6 7 8 9 10",
                "month         1 2 3 4 5 6 7 8 9 10 11 12",
                "day of week   1 2 3 4 5",
                "command       /usr/bin/find"

        );

        Assertions.assertEquals(expectedOutput, result);
    }
}

// things ot check
// "abc" so non numeral
// value valid e.g. can't have 13 for months, values to be withing min and max of enum
