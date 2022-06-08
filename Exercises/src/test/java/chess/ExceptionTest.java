package chess;

import org.junit.Test;

import static org.junit.Assert.*;

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

}
// error: 3 4 e5
// fail:  1 2  5 7 e3
// --  :   6 e1 e2 e4 e6 e7
