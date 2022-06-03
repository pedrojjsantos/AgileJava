package chess.pieces;

import chess.Board;
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

        assertTrue(blackPiece.isBlack());
        assertEquals(King.class, blackPiece.getClass());
        assertEquals('k', blackPiece.print());
    }

    @Test
    public void testKingMoves() {
        Board board = new Board();
        King king = Piece.createWhiteKing();

        List<String> possibleMoves = king.getPossibleMoves("d4", board);

        assertEquals(8, possibleMoves.size());
        assertTrue(possibleMoves.contains("c3"));
        assertTrue(possibleMoves.contains("c4"));
        assertTrue(possibleMoves.contains("c5"));
        assertTrue(possibleMoves.contains("d3"));
        assertTrue(possibleMoves.contains("d5"));
        assertTrue(possibleMoves.contains("e3"));
        assertTrue(possibleMoves.contains("e4"));
        assertTrue(possibleMoves.contains("e5"));

        board.put("a2", king);

        possibleMoves = king.getPossibleMoves("a2", board);

        assertEquals(5, possibleMoves.size());
        assertTrue(possibleMoves.contains("a1"));
        assertTrue(possibleMoves.contains("a3"));
        assertTrue(possibleMoves.contains("b1"));
        assertTrue(possibleMoves.contains("b2"));
        assertTrue(possibleMoves.contains("b3"));
    }
}
