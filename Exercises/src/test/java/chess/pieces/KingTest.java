package chess.pieces;

import chess.Board;
import chess.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KingTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhiteKing();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackKing();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(King.class, whitePiece.getClass());
        assertEquals('k', whitePiece.print());
        assertEquals(0, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertEquals(King.class, blackPiece.getClass());
        assertEquals('K', blackPiece.print());
        assertEquals(0, blackPiece.getStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testKingMoves() {
        Board board = new Board();
        King king = Piece.createWhiteKing();

        List<Position> possibleMoves = king.getPossibleMoves(new Position("d4"), board);

        assertEquals(8, possibleMoves.size());
        assertContains(possibleMoves, "c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5");

        possibleMoves = king.getPossibleMoves(new Position("a2"), board);

        assertEquals(5, possibleMoves.size());
        assertContains(possibleMoves, "a1", "a3", "b1", "b2", "b3");
    }
}
