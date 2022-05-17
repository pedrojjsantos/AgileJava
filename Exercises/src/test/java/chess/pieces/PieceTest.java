package chess.pieces;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PieceTest {
    @Test
    public void testCreate() {
        Piece pawnWhite = Piece.createWhite(Piece.PAWN, 'p');
        assertEquals(Piece.WHITE, pawnWhite.getColor());
        assertEquals("pawn", pawnWhite.getName());
        assertEquals('p', pawnWhite.print());

        Piece rookBlack = Piece.createBlack(Piece.ROOK, 'R');
        assertEquals(Piece.BLACK, rookBlack.getColor());
        assertEquals("rook", rookBlack.getName());
        assertEquals('R', rookBlack.print());
    }

    @Test
    public void testCount() {
        Piece.resetCount();
        assertEquals(0, Piece.getCountWhite());
        createWhitePiece();
        assertEquals(1, Piece.getCountWhite());
        createWhitePiece();
        assertEquals(2, Piece.getCountWhite());


        assertEquals(0, Piece.getCountBlack());
        createBlackPiece();
        assertEquals(1, Piece.getCountBlack());
        createBlackPiece();
        assertEquals(2, Piece.getCountBlack());

        assertEquals(2, Piece.getCountWhite());
    }

    private Piece createWhitePiece() {
        return Piece.createWhite("", 'p');

    }

    private Piece createBlackPiece() {
        return Piece.createBlack("", 'P');
    }
}