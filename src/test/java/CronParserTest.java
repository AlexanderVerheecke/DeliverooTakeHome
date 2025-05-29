import org.example.Cron;
import org.example.CronParser;
import org.junit.jupiter.api.Test;

public class CronParserTest {
    CronParser cronParser = new CronParser();
    Cron cron = Cron.builder().minutes("*/15").hours("0").dayOfMonth("1,15").month("*").dayOfWeek("1-5").command("/usr/bin/find").build();

    @Test
    void testParse() {
        cronParser.parse(cron);

    }

}