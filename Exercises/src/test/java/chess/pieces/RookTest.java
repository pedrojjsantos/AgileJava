package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class RookTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhiteRook();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackRook();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Rook.class, whitePiece.getClass());
        assertEquals('r', whitePiece.print());
        assertEquals(5, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Rook.class, blackPiece.getClass());
        assertEquals('R', blackPiece.print());
        assertEquals(5, blackPiece.getStrength(), STRENGTH_PRECISION);
    }
}
