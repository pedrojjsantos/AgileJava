package etc;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static etc.CustomFormatter.COUNTER_FORMAT;
import static etc.CustomFormatter.DEFAULT_FORMAT;
import static org.junit.Assert.assertEquals;

public class CustomFormatterTest {
    private Logger logger;

    @Before
    public void setUp() {
        logger = Logger.getLogger(CustomFormatterTest.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
    }

    @Test
    public void testFormatterWithCounter() {
        CounterHandler handler = new CounterHandler();
        logger.addHandler(handler);

        StringBuilder logMessages = new StringBuilder();

        String[] messages = {"Okay!", "Go", "Go NOW!", "Its Fine!"};
        Level[] levels = {Level.SEVERE, Level.INFO, Level.SEVERE, Level.FINE};

        for (int i = 0; i < messages.length; i++) {
            logger.log(levels[i], messages[i]);

            String msg = String.format(COUNTER_FORMAT,
                    levels[i], messages[i],
                    levels[i], handler.getCount(levels[i]));
            logMessages.append(msg);
        }

        assertEquals(logMessages.toString(), handler.getLogMessages());
    }

    @Test
    public void testFormatterWithoutCounter() {
        CounterHandler handler = new CounterHandler();
        handler.setFormatter(new CustomFormatter());
        logger.addHandler(handler);

        StringBuilder logMessages = new StringBuilder();

        String[] messages = {"Okay!", "Go", "Go NOW!", "Its Fine!"};
        Level[] levels = {Level.SEVERE, Level.INFO, Level.SEVERE, Level.FINE};

        for (int i = 0; i < messages.length; i++) {
            logger.log(levels[i], messages[i]);

            String msg = String.format(DEFAULT_FORMAT,
                    levels[i], messages[i]);
            logMessages.append(msg);
        }

        assertEquals(logMessages.toString(), handler.getLogMessages());
    }
}
