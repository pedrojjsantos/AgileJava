package chess.pieces;

import chess.Board;
import chess.Position;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void testRookMoves() {
        Board board = new Board();
        Rook rook = Piece.createBlackRook();

        List<Position> possibleMoves = rook.getPossibleMoves(new Position("e3"), board);

        assertEquals(14, possibleMoves.size());

        //File
        assertContains(possibleMoves, "e1", "e2", "e4", "e5", "e6", "e7", "e8");
        //Rank
        assertContains(possibleMoves, "a3", "b3", "c3", "d3", "f3", "g3", "h3");

    }
}
