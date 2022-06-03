package chess.pieces;

import chess.Board;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueenTest {
    @Test
    public void testCreate() {
        Queen whiteQueen = Piece.createWhiteQueen();
        Queen blackQueen = Piece.createBlackQueen();

        assertTrue(whiteQueen.isWhite());
        assertEquals(Piece.Type.QUEEN, whiteQueen.getType());
        assertEquals('q', whiteQueen.print());

        assertTrue(blackQueen.isBlack());
        assertEquals(Piece.Type.QUEEN, blackQueen.getType());
        assertEquals('q', blackQueen.print());
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
