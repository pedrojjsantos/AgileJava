package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BishopTest {
    @Test
    public void testCreate() {
        Bishop whiteBishop = Piece.createWhiteBishop();
        Bishop blackBishop = Piece.createBlackBishop();

        assertTrue(whiteBishop.isWhite());
        assertEquals(Piece.Type.BISHOP, whiteBishop.getType());
        assertEquals('b', whiteBishop.print());

        assertTrue(blackBishop.isBlack());
        assertEquals(Piece.Type.BISHOP, blackBishop.getType());
        assertEquals('b', blackBishop.print());
    }
}
