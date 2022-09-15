package etc;

import org.junit.Test;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExceptionTest {
    private final String BLOWS_UP_MSG = "Somebody should catch this!";
    @Test
    public void testBlowsUp() {
        try {
            blowsUp();
            fail("Didn't blow up! :(");
        } catch (SimpleException e) {
            assertEquals(BLOWS_UP_MSG, e.getMessage());
        }
    }

    @Test
    public void testRethrows() {
        try {
            rethrows();
            fail("Didn't blow up! :(");
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            assertEquals(SimpleException.class, cause.getClass());
            assertEquals(BLOWS_UP_MSG, cause.getMessage());
        }
    }

    private void blowsUp() throws SimpleException {
        throw new SimpleException(BLOWS_UP_MSG);
    }

    private void rethrows() throws RuntimeException {
        try {
            blowsUp();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWithProblems() {
        try {
            doSomething();
            fail("no exception");
        }
        catch (Exception success) {}
    }
    void doSomething() throws Exception {
        throw new Exception("blah");
    }

    @Test
    public void testReverseLog() {
        Logger logger = Logger.getLogger(this.getClass().getName());
        MockHandler handler = new MockHandler();
        logger.addHandler(handler);

        Throwable exception = getException();
        StackTraceElement[] stackTrace = exception.getStackTrace();

        log(logger, exception);

        Throwable loggedException = handler.getThrowable();
        StackTraceElement[] loggedStackTrace = loggedException.getStackTrace();

        assertEquals(exception.getClass(), loggedException.getClass());
        assertEquals(stackTrace.length, loggedStackTrace.length);

        int last = stackTrace.length - 1;

        for (int i = 0; i <= last; i++)
            assertEquals(stackTrace[i], loggedStackTrace[last - i]);
    }

    private Throwable getException() {
        Throwable exception = null;

        try {
            rethrows();
            fail("Expected Exception");
        }
        catch (Throwable e) {
            exception = e;
        }
        return exception;
    }

    private void log(Logger logger, Throwable exception) {
        if (!logger.isLoggable(Level.FINE))
            logger.setLevel(Level.FINE);

        exception.setStackTrace(reverse(exception.getStackTrace()));

        logger.log(Level.FINE, "", exception);
    }

    private <T> T[] reverse(T[] list) {
        int last = list.length - 1;

        for (int i = 0; i < list.length / 2; i++)
            swap(list, i, last - i);

        return list;
    }

    private <T> void swap(T[] list, int i, int j) {
        T tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    private static class MockHandler extends Handler {
        private LogRecord record;
        @Override
        public void publish(LogRecord record) {
            this.record = record;
        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }

        public Throwable getThrowable() {
            return record.getThrown();
        }
    }
}
// error: 3 4 e5
// fail:  1 2  5 7 e3
// --  :   6 e1 e2 e4 e6 e7
