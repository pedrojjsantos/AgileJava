package chess;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PositionTest {
    @Test
    public void testCreate() {
        Position posFromString = new Position("a8");
        verifyCreation(posFromString, "a8");

        Position posFromInt = new Position(3, 5); // "d6"
        verifyCreation(posFromInt, "d6");

        Position posFromChar = new Position('g', '8');
        verifyCreation(posFromChar, "g8");
    }

    private void verifyCreation(Position position, String strPos) {
        int file = strPos.charAt(0) - 'a';
        int rank = strPos.charAt(1) - '1';

        assertEquals(strPos, position.toString());

        assertEquals(strPos.charAt(0), position.getFileChar());
        assertEquals(strPos.charAt(1), position.getRankChar());

        assertEquals(file, position.getFileInt());
        assertEquals(rank, position.getRankInt());
    }

    @Test
    public void testEquality() {
        Position x1 = new Position("g7");
        Position x2 = new Position(6, 6);
        Position x3 = new Position('g', '7');

        Position y = new Position("a1");

        assertNotEquals(x1, y);
        assertNotEquals(x2, y);
        assertNotEquals(x3, y);

        // reflexivity
        assertEquals(x1, x1);

        // transitivity
        assertEquals(x1, x2);
        assertEquals(x2, x3);
        assertEquals(x1, x3);

        // symmetry
        assertEquals(x1, x2);
        assertEquals(x2, x1);

        // consistency
        assertEquals(x1, x2);

        // comparison to null
        assertFalse(x1.equals(null));

        // apples & oranges
        assertFalse(x1.equals(31415));
    }
}
