package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhiteKnight();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackKnight();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Knight.class, whitePiece.getClass());
        assertEquals('n', whitePiece.print());
        assertEquals(2.5, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Knight.class, blackPiece.getClass());
        assertEquals('n', blackPiece.print());
        assertEquals(2.5, blackPiece.getStrength(), STRENGTH_PRECISION);
    }
}
