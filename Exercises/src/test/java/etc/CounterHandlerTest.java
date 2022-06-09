package etc;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class CounterHandlerTest {
    @Test
    public void testCounter() {
        Logger logger = Logger.getLogger(CounterHandlerTest.class.getName());
        CounterHandler handler = new CounterHandler();
        logger.addHandler(handler);

        assertEquals(0, handler.getCount(Level.SEVERE));
        assertEquals(0, handler.getCount(Level.WARNING));
        assertEquals(0, handler.getCount(Level.INFO));
        assertEquals(0, handler.getCount(Level.CONFIG));
        assertEquals(0, handler.getCount(Level.FINE));
        assertEquals(0, handler.getCount(Level.FINER));
        assertEquals(0, handler.getCount(Level.FINEST));

        logger.severe("");
        assertEquals(1, handler.getCount(Level.SEVERE));
        logger.severe("");
        assertEquals(2, handler.getCount(Level.SEVERE));
        logger.severe("");
        assertEquals(3, handler.getCount(Level.SEVERE));

        logger.info("");
        logger.info("");
        logger.info("");
        assertEquals(3, handler.getCount(Level.INFO));

    }
}
