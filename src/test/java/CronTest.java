import org.example.Cron;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CronTest {

    @Test
    void invalidCronThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // has 7 elements, test causing thrown exception
            Cron cron = Cron.fromString("test */15 0 1,15 * 1-5 /usr/bin/find");
        });
    }

    @Test
    void noAssertionThrown() {
        Assertions.assertDoesNotThrow(() -> {
            Cron cron = Cron.fromString("*/15 0 1,15 * 1-5 /usr/bin/find");
        });
    }

    @Test
    void expandsStars(){
        Cron cron = Cron.fromString("* * * * * /usr/bin/find");
    }

    @Test
    void expandDashes(){
        Cron cron = Cron.fromString("1-5 0-5 1-20 1-2 1-2 /usr/bin/find"); // -
    }

}