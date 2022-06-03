package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RookTest {
    @Test
    public void testCreate() {
        Rook whiteRook = Piece.createWhiteRook();
        Rook blackRook = Piece.createBlackRook();

        assertTrue(whiteRook.isWhite());
        assertEquals(Piece.Type.ROOK, whiteRook.getType());
        assertEquals('r', whiteRook.print());

        assertTrue(blackRook.isBlack());
        assertEquals(Piece.Type.ROOK, blackRook.getType());
        assertEquals('r', blackRook.print());
    }
}
