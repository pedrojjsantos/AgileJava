package chess.pieces;

import chess.Board;
import chess.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KnightTest extends PieceTest {
    @Override
    protected Piece createWhitePiece() {
        return Piece.createWhiteKnight();
    }

    @Override
    protected Piece createBlackPiece() {
        return Piece.createBlackKnight();
    }

    @Override
    protected void verifyCreation(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertSame(Knight.class, whitePiece.getClass());
        assertEquals('n', whitePiece.print());
        assertEquals(2.5, whitePiece.getStrength(), STRENGTH_PRECISION);

        assertTrue(blackPiece.isBlack());
        assertSame(Knight.class, blackPiece.getClass());
        assertEquals('N', blackPiece.print());
        assertEquals(2.5, blackPiece.getStrength(), STRENGTH_PRECISION);
    }

    @Test
    public void testKnightMoves() {
        Board board = new Board();
        Knight knight = Piece.createBlackKnight();

        List<Position> possibleMoves = knight.getPossibleMoves(new Position("e5"), board);

        assertEquals(8, possibleMoves.size());

        assertContains(possibleMoves, "d7", "f7", "d3", "f3", "g4", "g6", "c4", "c6");
    }
}
