package chess.pieces;

import chess.Board;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingTest {
    @Test
    public void testCreate() {
        King whiteKing = Piece.createWhiteKing();
        King blackKing = Piece.createBlackKing();

        assertTrue(whiteKing.isWhite());
        assertEquals(Piece.Type.KING, whiteKing.getType());
        assertEquals('k', whiteKing.print());

        assertTrue(blackKing.isBlack());
        assertEquals(Piece.Type.KING, blackKing.getType());
        assertEquals('k', blackKing.print());
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
