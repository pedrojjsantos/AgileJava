package chess.pieces;

import org.junit.Test;

import static chess.pieces.Piece.*;
import static org.junit.Assert.*;

abstract public class PieceTest {
    protected final double STRENGTH_PRECISION = 0.05;

    abstract protected Piece createWhitePiece();
    abstract protected Piece createBlackPiece();
    abstract protected void verifyCreation(Piece whitePiece, Piece blackPiece);

    @Test
    public void testCreate() {
        Piece whitePiece = createWhitePiece();
        Piece blackPiece = createBlackPiece();

        assertTrue(whitePiece.isWhite());
        assertTrue(blackPiece.isBlack());

        verifyCreation(whitePiece, blackPiece);
    }
    @Test
    public void testIsEqual() {
        Piece whitePawn1 = Piece.createWhitePawn();
        Piece whitePawn2 = Piece.createWhitePawn();
        Piece blackPawn  = Piece.createBlackPawn();

        assertTrue(whitePawn1.isEqualTo(whitePawn2));
        assertTrue(whitePawn2.isEqualTo(whitePawn1));

        assertFalse(whitePawn1.isEqualTo(blackPawn));
        assertFalse(blackPawn.isEqualTo(whitePawn1));

    }

    @Test
    public void testStrength() {
        verifyStrength(1.0, Piece.createWhitePawn());
        verifyStrength(9.0, Piece.createWhiteQueen());
        verifyStrength(5.0, Piece.createBlackRook());
        verifyStrength(2.5, Piece.createBlackKnight());
        verifyStrength(5.0, Piece.createWhiteRook());
        verifyStrength(9.0, Piece.createBlackQueen());
    }

    private void verifyStrength(double strength, Piece piece) {
        assertEquals(strength, piece.getStrength(), 0.05);
    }

    @Test
    public void testCompare() {
        Piece whiteQueen = createWhiteQueen();
        Piece blackRook = createBlackRook();
        Piece blackQueen = createBlackQueen();

        assertTrue(whiteQueen.compareTo(blackRook) < 0);
        assertTrue(blackRook.compareTo(whiteQueen) > 0);
        assertEquals(0, whiteQueen.compareTo(blackQueen));
        assertEquals(0, blackQueen.compareTo(whiteQueen));
    }
}