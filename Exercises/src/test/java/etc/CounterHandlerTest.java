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
        logger.setLevel(Level.ALL);
        logger.addHandler(handler);

        assertEquals(0, handler.getCount(Level.SEVERE));
        assertEquals(0, handler.getCount(Level.WARNING));
        assertEquals(0, handler.getCount(Level.INFO));
        assertEquals(0, handler.getCount(Level.CONFIG));
        assertEquals(0, handler.getCount(Level.FINE));
        assertEquals(0, handler.getCount(Level.FINER));
        assertEquals(0, handler.getCount(Level.FINEST));

        logger.fine("");
        assertEquals(1, handler.getCount(Level.FINE));
        logger.fine("");
        assertEquals(2, handler.getCount(Level.FINE));
        logger.fine("");
        assertEquals(3, handler.getCount(Level.FINE));

        logger.finer("");
        logger.finer("");
        logger.finer("");
        assertEquals(3, handler.getCount(Level.FINER));

    }
}
