package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class BishopTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhiteBishop();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackBishop();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Bishop.class, whitePiece.getClass());
        assertEquals('b', whitePiece.print());
        assertEquals(3, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Bishop.class, blackPiece.getClass());
        assertEquals('b', blackPiece.print());
        assertEquals(3, blackPiece.getStrength(), STRENGTH_PRECISION);
    }
}
