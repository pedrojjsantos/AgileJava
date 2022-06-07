package sis.studentinfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScorerTest {
    @Test
    public void testCaptureScore() {
        Scorer scorer = new Scorer();
        assertEquals(75, scorer.score("75"));
    }

    @Test
    public void testBadScoreEntered() {
        Scorer scorer = new Scorer();
        try {
            scorer.score("abd");
            fail("expected NumberFormatException on bad input");
        } catch (NumberFormatException badInput) {
        }
    }

    @Test
    public void testIsValid() {
        Scorer scorer = new Scorer();
        assertTrue(scorer.isValid("75"));
        assertFalse(scorer.isValid("bd"));
    }
}
