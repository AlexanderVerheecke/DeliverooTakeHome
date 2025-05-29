import org.example.Cron;
import org.example.CronParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CronParserTest {
    CronParser cronParser = new CronParser();
    Cron cron = Cron.builder().minutes("*/15").hours("0").dayOfMonth("1,15").month("*").dayOfWeek("1-5").command("/usr/bin/find").build();

    @Test
    void invalidCronThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CronParser cronParser = new CronParser();
            // has 7 elements, test causing thrown exception
            Cron cron = Cron.fromString("test */15 0 1,15 * 1-5 /usr/bin/find");
            cronParser.parse(cron);
        });
    }

}