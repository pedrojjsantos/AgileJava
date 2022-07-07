package chess.pieces;

import chess.Position;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NoPieceTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.noPiece();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.noPiece();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertFalse(whitePiece.isWhite());
        assertSame(NoPiece.class, whitePiece.getClass());
        assertEquals('.', whitePiece.print());
        assertEquals(0, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertFalse(blackPiece.isBlack());
        assertSame(NoPiece.class, blackPiece.getClass());
        assertEquals('.', blackPiece.print());
        assertEquals(0, blackPiece.getStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testNoPieceMoves() {
        assertEquals(0, Piece.noPiece().getPossibleMoves(new Position("d4")).size());
    }
}
