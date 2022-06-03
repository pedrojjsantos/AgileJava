package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightTest {
    @Test
    public void testCreate() {
        Knight whiteKnight = Piece.createWhiteKnight();
        Knight blackKnight = Piece.createBlackKnight();

        assertTrue(whiteKnight.isWhite());
        assertEquals(Piece.Type.KNIGHT, whiteKnight.getType());
        assertEquals('n', whiteKnight.print());

        assertTrue(blackKnight.isBlack());
        assertEquals(Piece.Type.KNIGHT, blackKnight.getType());
        assertEquals('n', blackKnight.print());
    }
}
