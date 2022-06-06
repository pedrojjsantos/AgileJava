package chess.pieces;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class PawnTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhitePawn();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackPawn();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Pawn.class, whitePiece.getClass());
        assertEquals('p', whitePiece.print());
        assertEquals(1, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Pawn.class, blackPiece.getClass());
        assertEquals('p', blackPiece.print());
        assertEquals(1, blackPiece.getStrength(), STRENGTH_PRECISION);
    }
}
