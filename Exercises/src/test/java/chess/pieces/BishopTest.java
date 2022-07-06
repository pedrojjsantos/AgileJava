package chess.pieces;

import chess.Board;
import chess.Position;
import org.junit.Test;

import java.util.List;

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
        assertEquals('B', blackPiece.print());
        assertEquals(3, blackPiece.getStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testBishopMoves() {
        Board board = new Board();
        Bishop bishop = Piece.createBlackBishop();

        List<Position> possibleMoves = bishop.getPossibleMoves(new Position("d3"), board);

        assertEquals(11, possibleMoves.size());

        //Diagonals
        assertContains(possibleMoves, "b1", "c2", "e4", "f5", "g6", "h7");
        assertContains(possibleMoves, "a6", "b5", "c4", "e2", "f1");
    }
}
