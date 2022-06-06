package chess.pieces;

import chess.Board;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class QueenTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhiteQueen();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackQueen();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Queen.class, whitePiece.getClass());
        assertEquals('q', whitePiece.print());
        assertEquals(9, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Queen.class, blackPiece.getClass());
        assertEquals('q', blackPiece.print());
        assertEquals(9, blackPiece.getStrength(), STRENGTH_PRECISION);
    }
    @Test
    public void testQueenMoves() {
        Board board = new Board();
        Queen queen = Piece.createBlackQueen();

        List<String> possibleMoves = queen.getPossibleMoves("d3", board);

        assertEquals(25, possibleMoves.size());
        assertTrue(possibleMoves.contains("d1"));
        assertTrue(possibleMoves.contains("d2"));
        assertTrue(possibleMoves.contains("d4"));
        assertTrue(possibleMoves.contains("d5"));
        assertTrue(possibleMoves.contains("d6"));
        assertTrue(possibleMoves.contains("d7"));
        assertTrue(possibleMoves.contains("d8"));

        assertTrue(possibleMoves.contains("a3"));
        assertTrue(possibleMoves.contains("b3"));
        assertTrue(possibleMoves.contains("c3"));
        assertTrue(possibleMoves.contains("e3"));
        assertTrue(possibleMoves.contains("f3"));
        assertTrue(possibleMoves.contains("g3"));
        assertTrue(possibleMoves.contains("h3"));

        assertTrue(possibleMoves.contains("b1"));
        assertTrue(possibleMoves.contains("c2"));
        assertTrue(possibleMoves.contains("e4"));
        assertTrue(possibleMoves.contains("f5"));
        assertTrue(possibleMoves.contains("g6"));
        assertTrue(possibleMoves.contains("h7"));

        assertTrue(possibleMoves.contains("a6"));
        assertTrue(possibleMoves.contains("b5"));
        assertTrue(possibleMoves.contains("c4"));
        assertTrue(possibleMoves.contains("e2"));
        assertTrue(possibleMoves.contains("f1"));
    }
}
