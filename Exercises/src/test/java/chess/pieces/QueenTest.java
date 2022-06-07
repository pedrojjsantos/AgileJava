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

        //File
        assertContains(possibleMoves, "d1", "d2", "d4", "d5", "d6", "d7", "d8");

        //Rank
        assertContains(possibleMoves, "a3", "b3", "c3", "e3", "f3", "g3", "h3");

        //Diagonals
        assertContains(possibleMoves, "b1", "c2", "e4", "f5", "g6", "h7");
        assertContains(possibleMoves, "a6", "b5", "c4", "e2", "f1");
    }
}
