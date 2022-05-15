package chess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharacterTest {
    @Test
    public void testWhitespace() {
        assertTrue(Character.isWhitespace(' '));
        assertTrue(Character.isWhitespace('\t'));
        assertTrue(Character.isWhitespace('\n'));

        // Other character that returns true '\r'
        assertTrue(Character.isWhitespace('\r'));
    }

    @Test
    public void testIdentifierLetters() {
        final String letters = "abcdefghijklmnopqrstuvwxyz";

        for (char c : letters.toCharArray()) {
            assertTrue(Character.isJavaIdentifierStart(c));
            assertTrue(Character.isJavaIdentifierPart(c));
        }
        for (char c : letters.toUpperCase().toCharArray()) {
            assertTrue(Character.isJavaIdentifierStart(c));
            assertTrue(Character.isJavaIdentifierPart(c));
        }
    }

    @Test
    public void testIdentifierNumbers() {
        final String numbers = "0123456789";

        for (char c : numbers.toCharArray()) {
            assertFalse(Character.isJavaIdentifierStart(c));
            assertTrue(Character.isJavaIdentifierPart(c));
        }
    }
}
